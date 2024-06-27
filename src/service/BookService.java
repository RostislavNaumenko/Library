package service;

import model.Book;
import repository.BookRepository;
import util.MyList;

import java.util.HashMap;
import java.util.Map;

public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Add books




        //TODO(Alla) Проверить если существует эта книга в нашем списке ( с таким именем и автором)
    // Поиск по названию и автору:



    public Book searchBooks(String title, String author) {
        for (Book book : bookRepository.getAllBooks()) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null; // Книга не найдена


    }
    @Override
    public String toString() {
        return "BookService{" +
                "bookRepository=" + bookRepository +
                ", bookMap=" + bookMap +
                '}';
    }







    // Get books

    public Book getBookById(int bookId){
        return bookRepository.getBookById(bookId);
    }

    public Book getBookByTitle(String title){
        return bookRepository.getBookByTitle(title);
    }

    public MyList<Book> getBooksByAuthor(String author){
        return bookRepository.getBooksByAuthor(author);
    }


    public MyList<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    //Take book

    //Поиск по ID: Если у вас есть уникальный идентификатор
    // (например, bookID),
    // вы можете использовать его для поиска книги:
    public Book searchBookByID(int bookID) {
        return bookRepository.getBookById(bookID);
    }

    public boolean bookIsTaken (int bookId){
        //TODO(Alla) Проверить не взята уже эта книга
        Book book = bookRepository.getBookById(bookId);
        if (book==null) {
            return  true; // уточнить
        }
        return  book.isTaken();
    }



    //Remove book


    public Book removeBook(int bookId){
        //TODO (Alla) Проверить на существование такого
        // id book
        Book book = bookRepository.getBookById(bookId);
        //return book !=null; // нужно решить что правильно возвращать это (в этой строке !=null) или в строке ниже
        return bookRepository.removeBook(bookId);
    }


//Использование Map: Мы также можем использовать Map для хранения книг по уникальному ключу
    // (например, по названию и автору). Это ускорит поиск:

    private Map<String, Book> bookMap = new HashMap<>();

    // Убедитесь, что у вас есть методы для добавления книг (addBook()) и проверки
    // наличия пользователя (userLogin()), как в вашем коде.


}

