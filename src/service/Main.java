package service;

import model.Book;
import model.BookStorage;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BookStorage bookStorage= new BookStorage();
        // Добавьте книги в библиотеку

        String desiredAuthor = "Петров В.В."; // Заменить на нужного автора
         ArrayList<Book> booksByAuthor =BookStorage.getBooksByAuthor (desiredAuthor);
        // здесь что-то не пошло с автором, нужно создать getBooksByAuthor, но вроде уже создано getBooksByAuthor

        if (booksByAuthor.isEmpty()) {
            System.out.println("Нет книг автора " + desiredAuthor);
        } else {
            System.out.println("Книги автора " + desiredAuthor + ":");
            for (Book book : booksByAuthor) {
                System.out.println(book.getTitle()); // Заменить на нужное поле (название книги)
            }
        }
    }
}