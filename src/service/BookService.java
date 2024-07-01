package service;

import model.Book;
import repository.BookRepository;
import util.MyList;

public class BookService {
    private final BookRepository bookRepository;


    public BookService() {
        this.bookRepository = new BookRepository();
    }

    //Add books

    public Book addBook(String title, String author){
        //TODO(Rostyslav) Проверить если существует эта книга в нашем списке ( с таким именем и автором)
        Book book = bookRepository.addBook(title, author);

        return book;
    }

    // Get books

    public MyList<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public Book getBookById(int bookId){
        return bookRepository.getBookById(bookId);
    }

    public Book getBookByTitle(String title){
        return bookRepository.getBookByTitle(title);
    }

    public MyList<Book> getBooksByAuthor(String author){
        return bookRepository.getBooksByAuthor(author);
    }

    public Book getBookByAuthorAndTitle(String title, String author){
        return bookRepository.getBookByAuthorAndTitle(title, author);
    }


    public MyList<Book> getAllTakenBooks(){
        return bookRepository.getAllTakenBooks();
    }

    //Take book

    //Взятие книги по id
    public boolean takeBook (int bookId){
        return  bookRepository.takeBook(bookId);
    }

    //Взятие книги по title и author
    public boolean takeBook (String title, String author){
        Book book = getBookByAuthorAndTitle(title, author);
        return bookRepository.takeBook(book.getBookId());
    }

    //Возвращение книг

    //Возвращение книги по id
    public boolean returnBook (int id){
        return bookRepository.returnBook(id);
    }

    //Возвращение книги по title и id
    public boolean returnBook (String title, String author){
        Book book = getBookByAuthorAndTitle(title, author);
        return bookRepository.returnBook(book.getBookId());

    }


    //Remove book

    public Book removeBook(int bookId){

        return bookRepository.removeBook(bookId);
    }

    //Sort

    //Сортировка книг по автору
    public MyList<Book> sortBooksByAuthor(String author){
        return bookRepository.sortBooksByAuthor(author);
    }

    public MyList<Book> sortAllBooksByTitle(){
        return bookRepository.sortAllBooksByTitle();
    }




}
