package repository;

import model.Book;
import util.MagicList;
import util.MyList;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BookRepository {

    private final MyList<Book> books;
    private final AtomicInteger currentId = new AtomicInteger(1);

    public BookRepository() {
        this.books = new MagicList<>();
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

    public MyList<Book> getFreeBooks() {
        MyList<Book> freeBooks = new MagicList<>();
        for (Book book : books) {
            if (!book.isTaken()) {
                freeBooks.add(book);
            }
        }
        return freeBooks;
    }



    public Book getBookByAuthorAndTitle(String title, String author){

        for (Book book : books){
            if(book.getAuthor().equalsIgnoreCase(author) && book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }

        return null;
    }


    public MyList<Book> getAllTakenBooks() {
        MyList<Book> takenBooks = new MagicList<>();
        for (Book book : books) {
            if (book.isTaken()) {
                takenBooks.add(book);
            }
        }
        return takenBooks;
    }

    //UPDATE

    //Взятие книги по id
    public boolean takeBook (int id) {
        Book book = getBookById(id);
        if(book == null || book.isTaken()){
            return false;
        }

        book.setTaken(true);
        return true;
    }


    //Возвращение книги по id
    public boolean returnBook (int id) {
        Book book = getBookById(id);
        if(book == null || !book.isTaken()){
            return false;
        }

        book.setTaken(false);
        return true;
    }


    //Delete
    public Book removeBook(int bookId){
        Book book = getBookById(bookId);
        if(book == null) return null;

        books.remove(book);
        return book;
    }

    //Sort

    //TODO Ask Sergey
    public MyList<Book> sortBooksByAuthor(String author){

        MyList<Book> authorBooks = new MagicList<>();

        for (Book book : books){
            if(book.getAuthor().equals(author)){
                authorBooks.add(book);
            }
        }

        MyList<Book> sortedAuthorBooks = (MyList<Book>) Comparator.comparing(Book :: getTitle);

        return sortedAuthorBooks;

    }

    public MyList<Book> sortAllBooksByTitle(){
        MyList<Book> sortedBooks = new MagicList<>();
        for(Book book : books){
            sortedBooks.add(book);
        }
        return (MyList<Book>) Comparator.comparing(Book::getTitle);
    }


}
