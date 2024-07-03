package test;

import model.Book;
import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import repository.BookRepository;
import repository.UserRepository;
import service.BookService;
import util.MyList;

import java.io.Reader;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestLibrary {

    private final BookRepository bookRepository = new BookRepository();
    private final UserRepository userRepository = new UserRepository();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final BookService service= new BookService();

   @ParameterizedTest
   @MethodSource ("takeValidBook")
    void testTakeValidBook(int id, String titleBook, String userName, String password){
        MyList<Book> busyBooks = service.getAllTakenBooks();
        int sizeBusy = busyBooks.size();
        service.authenticateUser(userName, password);

        boolean isSuccesses = service.takeBook();
        busyBooks = service.getAllTakenBooks();
        MyList<Book> availableBooks = service.getAllFreeBooks();


        Book book = service.getBookById(id).orElse(null);
        assertTrue(book.isTaken());
        assertTrue(busyBooks.contains(book));
        assertFalse(availableBooks.contains(book));
        assertEquals(sizeBusy + 1, busyBooks.size());
        assertTrue(isSuccesses);

        //realise Book
        boolean isRealised = service.takeBook(id);
        busyBooks = service.getAllTakenBooks();
        availableBooks = service.getAllFreeBooks();
        assertTrue(isRealised);
        assertFalse(busyBooks.contains(book));
        assertTrue(availableBooks.contains(book));
        assertFalse(book.isTaken());
    }

    private  static Stream<Arguments> dataTakeValidBook(){
        return Stream.of(
                Arguments.of(1, " Живая Вода", "User1", "Password1"),
                Arguments.of(9, "Cказки", "User2", "Password2"),
                Arguments.of(3, "Кулинария", "User14", "Password14")
        );
    }

    @Test
    void testBusyDuration(){
        MyList<Book> books = service.getAllBooks();
        for (Book book : books){
            long busyDuration = service.getBusyDaysForBook(book.getBookId());
         //   System.out.println(book.getTitle()) + " (" + book.isTaken() + "): " + busyDuration);

        }
    }

    @ParameterizedTest
    @MethodSource("dataValidUserCreate")
    void testCreateCorrectUser(String name, String password){
        service.authenticateUser( "User1", "Password1");
        // авторизация Админа
        //TODO дописать тест на роли

        MyList<User> users = service.getAllUsers();
        int size = users.size();
        User user = service.createUser(name, password);
        MyList<User> newUsers = service.getAllUsers();
        assertTrue(newUsers.contains(user));
        assertEquals(size + 1, newUsers.size());

    }

    private static Stream<Arguments> dataValidUserCreate(){
        return Stream.of(
                Arguments.of("valid@mail.com", "qwerty!1Q"),
                Arguments.of("test@user.net", "passwordE3$")
        );
    }

    @ParameterizedTest
    @MethodSource("dataInvalidUserCreate")
    void testCreateInvalidUser(String name, String password){
       MyList<User> users = service.getAllUsers();
       int size = users.size();
       User user = service.createUser(name, password);
       MyList<User> newUsers = service.getAllUsers();
       assertNull(user);
       assertEquals(size, newUsers.size());
    }

    private static  Stream<Arguments> dataInvalidUserCreate(){
       return Stream.of(
               Arguments.of("", "password"),
               Arguments.of("UserTest", ""),
               Arguments.of("UserTest", null),
               Arguments.of( "User1", "Password1"),
               Arguments.of( "User14", "Password14"),
               Arguments.of(null, "Password1")
       );
    }

    @ParameterizedTest
    @MethodSource("dataForTestTakeBooksByAuthor" )
    void testPredicate(String author, int count){
       MyList<Book> booksByAuthor = service.getBooksByAuthor(author);
       assertEquals(count, booksByAuthor.size());
        System.out.println("Список книг по автору: " + author);
        for (Book book : booksByAuthor){
            System.out.println(book);
        }
        System.out.println("=====================================");
    }

    private static Stream<Arguments> dataForTestTakeBooksByAuthor(){
       return Stream.of(
               Arguments.of( " Джон Керри", 3),
               Arguments.of(" Robert Martin", 1),
               Arguments.of(" Тод Фридман", 2),
              Arguments.of( "Нет такого автора", 0)
       );
    }

    @ParameterizedTest
    @MethodSource("dataTestCorrectTitle")
    void testFindBookByCorrectId(int id, String title, String author){
       Book book = service.getBookById(id);
       assertEquals(id, book.getBookId());
       assertEquals(title, book.getTitle());
       assertEquals(author, book.getAuthor());
    }
    private static Stream<Arguments> dataTestCorrectTitle(){
       return Stream.of(
               Arguments.of(1, " Живая Вода", " Robert Martin" )
               Arguments.of(6, "String в действии ",  "Крейг Уоллс" )
               Arguments.of(3, "Java. Основы", " Кей Хорстман")
       );
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})

    void testGetById(int id){
       Book book = service.getBookById(id).orElse(null);
        System.out.println("test get: " + book);
        assertNotNull(book);
    }

    @ParameterizedTest
    @MethodSource("dataTestInvalidTitle")
    void testFindBookByInvalidId(int invalidId){
       Book book = service.getBookById(invalidId).orElse(null);
       assertNull(book);
    }

    private static Stream<Integer>dataTestInvalidTitle(){
       return Stream.of(
               14,
               1,
               25,
               55
       );
    }

    @Test
    void testGetAllBooks(){
       assertEquals(11, books.size());
    }

    @Test
    void testGetAllUsers(){
        service.authenticateUser( "User1", "Password1");
        //TODO тест на роли

        MyList<User> user = service.getAllUsers();
        assertEquals(8, users.size());

        //для юсеров
        for (User users : users){
            System.out.println(user + " | ");
            System.out.println(user.getCreateAt().format(formatter));
            System.out.println();
        }
    }

    @Test
    void testFindBooksByPartTitle(){
       String partTitle = "Java";
       MyList<Book> books = service.findBooksByPartTitle(partTitle);
       assertEquals(8, books.size());
        System.out.println(books)

        String partTitle1 = "Test";
        MyList<Book> books1 = service.findBooksByPartTitle(partTitle1);
        assertEquals(0, books1.size());
        System.out.println(books1);

    }

    @ParameterizedTest
    @MethodSource("dataAddBooks")
    void testAddBook(String title, String author, boolean flag ){
        service.authenticateUser( "User2", "Password2");
        //TODO ДОПИСАТЬ ТЕСТ ПРОВЕРКИ НА РОЛЬ

        MyList<Book> books = service.getAllBooks();
        int size = books.size();
        Book newBook = service.createNewBook(title, author);
        MyList<Book> newBooks = service.getAllBooks();
        if (flag){
            assertNotNull(newBooks);
            assertEquals(size +1, newBooks.size());
            assertTrue(newBooks.contains((newBook));
        } else {
            assertNull(newBook);
            assertEquals(books.size(), newBooks.size());
        }
    }

    static Stream<Arguments> dataAddBooks(){
       return Stream.of(
               Arguments.of("Tom and Jerry", "Illia Puh", false),
               Arguments.of( "Java 8. Руководство для начинающих", "Герберт Шилд", true),
               Arguments.of("Полет над гнездом кукушки", " Линда Поц", false),
               Arguments.of( "Java Performance. In-Depth Advice for Tuning and Programming Java 8,11, and Beyond", "Скотт Оакс", true )
       );
    }

    @ParameterizedTest
    @MethodSource("getUsersAuthData")
    void testAuthenticateUser(String name, String password, boolean flag){
       User user = service.getActiveUser();
       User authUser = service.authenticateUser(name, password);
       User active = service.getActiveUser();
        if (flag){
            assertNotNull(authUser);
            assertEquals(name, active.getEmail());
            assertEquals( password, active.getPassword());
            assertEquals(authUser, active);
        } else {
            assertNull(authUser);
            if ( user == null){
                assertNull(active, "Active null" );
            } else {
                assertEquals(user, active);
            }
        }
    }

    static Stream<Arguments> getUserAuthData() {
        return Stream.of(
                Arguments.of("User1", "Password1", true),
                Arguments.of("User1", "Password2", false),
                Arguments.of("User2", "Password2", true),
                Arguments.of("User", "Password1", false)
        );
    }
}




