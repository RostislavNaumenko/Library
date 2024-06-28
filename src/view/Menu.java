package view;

import model.BookStorage;
import service.BookService;
import service.UserService;

public class Menu {
    private final BookService bookService;
    private final UserService userService;

    public Menu(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    public void run() {
        showMenu();
    }

    private void showMenu() {


    }

    public static void main(String[] args) {
        BookStorage bookStorage = new BookStorage();

        // Заполните список книг
        // ..
    }
}
