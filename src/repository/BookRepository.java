package repository;

import model.Book;
import util.MagicList;
import util.MyList;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepository {

    private final MyList<Book> books = new MagicList<>();
    private final AtomicInteger currentId = new AtomicInteger(5);


    public BookRepository() {

    }

    {
        books.add(new Book(1,"Java for beginners","Stefan"));
        books.add(new Book(2,"The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(3,"1984", "George Orwell"));
        books.add(new Book(4,"To Kill a Mockingbird", "Harper Lee"));

    }

    //Add book
    public Book addBook (String title, String author){
        for(Book book : books){
            if(book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)){
                return null;
            }
        }
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
    public MyList<Book> sortBooksByAuthor(String author){

        MyList<Book> authorBooks = new MagicList<>();

        for (Book book : books){
            if(book.getAuthor().equals(author)){
                authorBooks.add(book);
            }
        }
        if(authorBooks == null) return null;
        MyList<Book> sortedAuthorBooks = (MyList<Book>) Comparator.comparing(Book :: getTitle);

        return sortedAuthorBooks;

    }

    public MyList<Book> sortAllBooksByTitle(){

        Book[] array = books.toArray();

        Arrays.sort(array, Comparator.comparing(Book::getTitle));
        MyList<Book> result = new MagicList<>(array);
        return result;
    }

}
