package view;

import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import service.BookService;
import service.UserService;

import java.util.Scanner;


public class Menu {
    private final BookService bookService;
    private final UserService userService;
    private final BookMenu bookMenu;
    private final UserMenu userMenu;
    private final AdminMenu adminMenu;

    private final Scanner scanner = new Scanner(System.in);

    public Menu() {
        this.bookService = new BookService();
        this.userService = new UserService();
        this.bookMenu = new BookMenu();
        this.userMenu = new UserMenu();
        this.adminMenu = new AdminMenu();
    }


    public void run() {
        while (true) {
            //    showMenu();
            showInitialMenu();
        }
    }

    private void showInitialMenu() {
        System.out.println("Добро пожаловать в меню");
        System.out.println("1 -> Вход для администратора");
        System.out.println("2 -> Вход для пользователя");
        System.out.println("0 -> Выход");

        System.out.println("\nСделайте выбор:");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) {
            System.out.println("До свидания");
            System.exit(0);
        }

        if (choice == 1) {
            loginAsAdmin();
        } else if (choice == 2) {
            userMenu.showUserMenu();
        } else {
            System.out.println("Некорректный выбор. Попробуйте снова.");
        }
    }

    private void loginAsAdmin() {
        System.out.println("Введите email:");
        String email = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        boolean isAuthenticated = userService.authenticate(email, password);
        if (isAuthenticated && userService.getActiveUser().getRole() == Role.ADMIN) {
            adminMenu.showAdminMenu();
        } else {
            System.out.println("Ошибка при входе. Попробуйте снова.");
        }
    }
//    private void showMenu() {
//        while (true) {
//            System.out.println("Добро пожаловать в меню");
//            System.out.println("1 -> Список всех книг");
//            System.out.println("2 -> Меню пользователей");
//            System.out.println("3 -> Меню администратора");
//            System.out.println("0 -> Выход");
//            System.out.println("\nСделайте выбор:");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            if (choice == 0) {
//                System.out.println("До свидания");
//                System.exit(0);
//                //todo  ( break; ?)
//
//            }
//            showSubMenu(choice);
//        }
//    }

//    private void showSubMenu(int choice) {
//        switch (choice) {
//            case 1:
//                bookMenu.showBookMenu();
//                break;
//            case 2:
//                userMenu.showUserMenu();
//                break;
//            case 3:
//                adminMenu.showAdminMenu();
//                break;
//            default:
//                System.out.println("Сделайте корректный выбор\n");
//
//        }
//
//    }

    public void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter ");
        scanner.nextLine();
    }

}
