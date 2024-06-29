package view;

import model.User;
import service.BookService;
import service.UserService;

import java.util.Scanner;


public class Menu {
    private final BookService bookService;
    private final UserService userService;
    private final BookMenu bookMenu;
    private final UserMenu userMenu;
    private final AdminMenu adminMenu;
    private final Scanner scanner;



    public Menu(BookService bookService, UserService userService, BookMenu bookMenu, UserMenu userMenu, AdminMenu adminMenu) {
        this.scanner = new Scanner(System.in);
        this.bookService = bookService;
        this.userService = userService;
        this.bookMenu = bookMenu;
        this.userMenu = userMenu;
        this.adminMenu = adminMenu;
    }

    public Menu(BookService bookService, UserService userService, BookMenu bookMenu, UserMenu userMenu, AdminMenu adminMenu, Scanner scanner) {

        this.bookService = bookService;
        this.userService = userService;
        this.bookMenu = bookMenu;
        this.userMenu = userMenu;
        this.adminMenu = adminMenu;
        this.scanner = scanner;
    }


    public void run() {

        showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println("Добро пожаловать в меню");
            System.out.println("1 -> Меню книг");
            System.out.println("2 -> Меню пользователей");
            System.out.println("3 -> Меню администратора");
            System.out.println("0 -> Выход");
            System.out.println("\nСделайте выбор:");

            int choice = this.scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания");
                System.exit(0);
                //todo  ( break; ?)

            }
            this.showSubMenu(choice);
        }
    }

    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                bookMenu.showBookMenu();
                break;
            case 2:
                userMenu.showUserMenu();
                break;
            case 3:
                adminMenu.showAdminMenu();
                break;
            default:
                System.out.println("Сделайте корректный выбор\n");

        }

    }

    public void waitRead () {
        System.out.println("\nДля продолжения нажмите Enter ");
        scanner.nextLine();
    }

}
