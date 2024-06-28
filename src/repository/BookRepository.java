package repository;

import model.Book;
import util.MagicList;
import util.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepository {

    private final MyList<Book> books;
    private final AtomicInteger currentId = new AtomicInteger(1);

    public BookRepository(MyList<Book> books) {
        this.books = books;
    }


    //Add book
    public Book addBook (String title, String author){
        Book book = new Book(currentId.getAndIncrement(), title, author);
        books.add(book);
        return book;
    }

    //Read
    public MyList<Book> getAllBooks() {
        return books;
    }

    public Book getBookById (int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public Book getBookByTitle(String title){
        for (Book book : books){
            if (book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }

        return null;
    }


    public MyList<Book> getBooksByAuthor(String author) {

        MyList<Book> authorBooks = new MagicList<>();

        for (Book book : books){
            if(book.getAuthor().equals(author)){
                authorBooks.add(book);
            }
        }

        return authorBooks;
    }

    //TODO Написать метод поиска книги по автору и названию книги
    //TODO (Alla) Написать метод вывода всех незанятых книг по автору

    public MyList<Book> getFreeBooks() {
        MyList<Book> freeBooks = new MagicList<>();
        for (Book book : books) {
            if (book.isTaken() == false) {
                freeBooks.add(book);
            }
        }
        return freeBooks;
    }

    //UPDATE
    public boolean takeBook (int id) {
        Book book = getBookById(id);
        if(book == null || book.isTaken()){
            return false;
        }

        book.setTaken(true);
        return true;
    }

    //Delete

    public Book removeBook(int bookId){
        Book book = getBookById(bookId);
        if(book == null) return null;

        books.remove(book);
        return book;
    }


}
