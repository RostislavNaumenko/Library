package view;

import model.Book;
import service.BookService;
import service.UserService;
import util.MyList;

import java.util.Scanner;

public class BookMenu  {

    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService;
    private final UserService userService;

    public BookMenu(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    public void showBookMenu() {
        while (true) {
            System.out.println("Меню книг");
            System.out.println("1 -> Список всех книг");
            System.out.println("2 -> Список всех свободных книг");
            System.out.println("3 -> Список всех книг, отсортированный по автору");
            System.out.println("4 -> Список всех книг, отсортированный по названию книги");
            System.out.println("5 -> Взятие книги из библиотеки");
            System.out.println("6 -> Возврат книги в библиотеку по id");
            //System.out.println("7 -> Возврат книги по названию и автору");
            System.out.println("7 -> Список всех книг, находящихся сейчас у читателей");
            System.out.println("0 -> Возврат в предыдущее меню");

            System.out.println("\nСделайте выбор пункта:");
            int actions = scanner.nextInt();
            scanner.nextLine();
            if (actions == 0)
                break;

            ActionMenuWithBooks(actions);

        }

    }

    private void ActionMenuWithBooks(int actions) {
        switch (actions) {
            case 1:
                //Список всех книг
                System.out.println("Список всех книг");
                showBooks();
                waitRead();
                break;
            case 2:
                //Список всех свободных книг
                System.out.println("Список всех свободных книг");
                System.out.println(bookService.getAllFreeBooks());
                waitRead();
                break;
            case 3:
                //Список всех книг, отсортированный по автору
                System.out.println("Введите автора:");
                String authorSearch = scanner.nextLine();
                MyList<Book> booksByAuthor = bookService.sortBooksByAuthor(authorSearch);
                System.out.println(booksByAuthor);
                waitRead();
                break;
            case 4:
                //Список всех книг, отсортированный по названию книги
                System.out.println("Нажмите Enter:");
                sortAllBooksByTitle();
                waitRead();
                break;
            case 5:
                //Взятие книги из библиотеки
                /*
                System.out.println("Взять книгу");
                User user = userService.getActiveUser();
                if (user == null) {
                    System.out.println("Вы должны авторизоваться: ");
                    waitRead();
                    break;
                }

                 */
                System.out.println(bookService.getAllFreeBooks());
                System.out.println("Введите id книги:");
                int idBook = scanner.nextInt();
                scanner.nextLine();
                if(!bookService.takeBook(idBook)){
                    System.out.println("Не найдена такая книга или она уже взята");
                    waitRead();
                    break;
                }else{
                    System.out.printf("Пользователь %s ВЗЯЛ из библиотеки книгу %s", userService.getActiveUser().getEmail(), idBook);
                    waitRead();
                    break;
                }

            case 6:
                //Возврат книги в библиотеку
                /*
                System.out.println("Возврат книги по id");
                User user1 = userService.getActiveUser();
                if (user1 == null) {
                    System.out.println("Вы должны авторизоваться: ");
                    waitRead();
                    break;
                }

                 */
                System.out.println(bookService.getAllTakenBooks());
                System.out.println("Введите id книги:");
                int id = scanner.nextInt();
                scanner.nextLine();
                if(!bookService.returnBook(id)){
                    System.out.println("Не найдена такая книга или она уже в библиотеке");
                    waitRead();
                    break;
                }else {
                    System.out.printf("Книга с id = %d была возвращена \n", id);
                    waitRead();
                    break;
                }


//            case 7:
//                /*
//
//                System.out.println("Возврат книги по названию и автору");
//                User user2 = userService.getActiveUser();
//                if (user2 == null) {
//                    System.out.println("Вы должны авторизоваться: ");
//                    waitRead();
//                    break;
//                }
//                */
//                System.out.println(bookService.getAllTakenBooks());
//                System.out.println("Введите название книги:");
//                String title = scanner.nextLine();
//                System.out.println("Введите автора книги:");
//                String author = scanner.nextLine();
//                bookService.returnBook(title, author);
//                System.out.printf("Книга с id = %d была возвращена \n", title, author);
//                waitRead();
//                break;


            case 7:
                //Список всех книг, находящихся сейчас у читателей
                System.out.println("Список всех взятых книг");
                System.out.println(bookService.getAllTakenBooks());
                waitRead();
                break;
            default:
                System.out.println("не корректный выбор\n");

        }



    }
    public void showBooks() {
        System.out.println(bookService.getAllBooks());
    }


    public void sortAllBooksByTitle () {
        System.out.println(bookService.sortAllBooksByTitle());
    }


    public void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter ");
        scanner.nextLine();
    }
}
