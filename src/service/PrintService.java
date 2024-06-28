package service;

import model.Book;
import repository.BookRepository;

public class PrintService {
    private BookRepository repository;

    public PrintService(BookRepository repository) {
        this.repository = repository;
    }

    public void printData() {
        System.out.println("Список книг (title):");
        for (Book book : repository.getAllBooks()) {
            System.out.println(" bookId = " + book.getBookId() + " Название книги (title): " + book.getTitle());
        }
    }
}