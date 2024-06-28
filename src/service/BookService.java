package service;

import model.Book;
import repository.BookRepository;
import util.MyList;

public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Add books

    public Book addBook(String title, String author){
        Book book = bookRepository.addBook(title, author);

        return book;
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

    public boolean takeBook (int bookId){
        return  bookRepository.takeBook(bookId);
    }

    //Remove book

    public Book removeBook(int bookId){
        return bookRepository.removeBook(bookId);
    }


}
