package model;

import java.util.ArrayList;
import java.util.List;

public class BookStorage {


    public static ArrayList<Book> getBooksByAuthor(String desiredAuthor)  {
            List<Book> availableBooks = new ArrayList<>();
            for (Book book : getBooksByAuthor(desiredAuthor)) {
                if (book.getAuthor().equals(desiredAuthor) && !book.isTaken()) {
                    availableBooks.add(book);
                }
            }
            return (ArrayList<Book>) availableBooks;
        }
    }


