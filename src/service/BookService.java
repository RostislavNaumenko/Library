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
        if(!isTitleValid(title) || !isAuthorValid(author)) return null;
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

    public Book getBookByTitleAndAuthor(String title, String author){
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
        Book book = getBookByTitleAndAuthor(title, author);
        return bookRepository.takeBook(book.getBookId());
    }

    //Возвращение книг

    //Возвращение книги по id
    public boolean returnBook (int id){
        return bookRepository.returnBook(id);
    }

    //Возвращение книги по title и id
    public boolean returnBook (String title, String author){
        Book book = getBookByTitleAndAuthor(title, author);
        return bookRepository.returnBook(book.getBookId());

    }


    //Remove book

    public Book removeBook(int bookId){

        return bookRepository.removeBook(bookId);
    }

    public Book removeBook(String title, String author){
        Book book = getBookByTitleAndAuthor(title, author);
        if(book == null) return null;
        return bookRepository.removeBook(book.getBookId());
    }

    //Sort

    //Сортировка книг по автору
    public MyList<Book> sortBooksByAuthor(String author){
        return bookRepository.sortBooksByAuthor(author);
    }

    public MyList<Book> sortAllBooksByTitle(){
        return bookRepository.sortAllBooksByTitle();
    }


    private boolean isTitleValid(String title){
        if (title == null || title.length() < 1 || title.length() > 100) return false;

        boolean validCharacters = true;
        boolean noLeadingOrTrailingSpaces = title.charAt(0) != ' ' && title.charAt(title.length() - 1) != ' ';
        boolean noConsecutiveSpaces = !title.contains("  ");

        for (char ch : title.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && ch != '\'' && ch != '-' && ch != ',' && ch != ' ' && ch!= '+') {
                validCharacters = false;
                break;
            }
        }

        return validCharacters && noLeadingOrTrailingSpaces && noConsecutiveSpaces;
    }

    private boolean isAuthorValid(String author){
        if (author == null || author.length() < 1 || author.length() > 50) return false;

        boolean validCharacters = true;
        boolean noLeadingOrTrailingSpaces = author.charAt(0) != ' ' && author.charAt(author.length() - 1) != ' ';
        boolean noConsecutiveSpaces = !author.contains("  ");
        boolean firstCharacterUpperCase = Character.isUpperCase(author.charAt(0));

        for (char ch : author.toCharArray()) {
            if (!Character.isLetter(ch) && ch != '\'' && ch != '-' && ch != ',' && ch != ' ') {
                validCharacters = false;
                break;
            }
        }

        return validCharacters && noLeadingOrTrailingSpaces && noConsecutiveSpaces && firstCharacterUpperCase;
    }





}
