package view;

import model.User;
import repository.BookRepository;
import repository.UserRepository;
import service.BookService;
import service.UserService;

import java.util.Scanner;


public class Menu {
    private final BookService bookService;
    private final UserService userService;
    private final BookMenu bookMenu ;
    private final UserMenu userMenu;
    private final AdminMenu adminMenu;

    private final Scanner scanner = new Scanner(System.in);

    public Menu() {
        this.bookService = new BookService();
        this.userService = new UserService();
        this.bookMenu = new BookMenu(bookService, userService);
        this.userMenu = new UserMenu();
        this.adminMenu = new AdminMenu(bookService, userService);
    }


    public void run() {

        showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println("Добро пожаловать в меню");
            System.out.println("1 -> Список всех книг");
            System.out.println("2 -> Меню пользователей");
            System.out.println("3 -> Меню администратора");
            System.out.println("0 -> Выход");
            System.out.println("\nСделайте выбор:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания");
                System.exit(0);
                //todo  ( break; ?)

            }
            showSubMenu(choice);
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
