package view;

import model.User;
import service.BookService;
import service.UserService;

import java.util.Scanner;


public class Menu {
    private final BookService bookService;
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
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
                showBookMenu();
                break;
            case 2:
                showUserMenu();
                break;
            case 3:
                showAdminMenu();
                break;
            default:
                System.out.println("Сделайте корректный выбор\n");

        }

    }

    private void showBookMenu() {
        while (true) {
            System.out.println("Меню книг");
            System.out.println("1 -> Список всех книг");
            System.out.println("2 -> Список всех свободных книг");
            System.out.println("3 -> Список всех книг, отсортированный по автору");
            System.out.println("4 -> Список всех книг, отсортированный по названию книги");
            System.out.println("5 -> Взятие книги из библиотеки");
            System.out.println("6 -> Возврат книги в библиотеку");
            System.out.println("7 -> Список всех книг, находящихся сейчас у читателей");
            System.out.println("0 -> Возврат в предыдущее меню");

            System.out.println("\nСделайте выбор пункта:");
            int actions = scanner.nextInt();
            scanner.nextLine();
            if (actions == 0) break;

            ActionMenuWithBooks(actions);

        }

    }

    private void ActionMenuWithBooks(int actions) {
        switch (actions) {
            case 1:
                // Список всех книг
                break;
            case 2:
                //Список всех свободных книг
                break;
            case 3:
                //Список всех книг, отсортированный по автору
                break;
            case 4:
                //Список всех книг, отсортированный по названию книги
                break;
            case 5:
                //Взятие книги из библиотеки
                break;
            case 6:
                //Возврат книги в библиотеку
                break;
            case 7:
                //Список всех книг, находящихся сейчас у читателей
                break;
            default:
                System.out.println("не корректный выбор\n");

        }

    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("Меню Администратора");
            System.out.println("1 -> Добавление книги");
            System.out.println("2 -> Удаление книги");
            System.out.println("0 -> Возврат в предыдущее меню");

            System.out.println("\nСделайте выбор пункта:");

            int control = scanner.nextInt();
            scanner.nextLine();
            if (control == 0) break;

            AdminMenuWithBooks(control);

        }


    }

    private void AdminMenuWithBooks(int control) {
        switch (control) {
            case 1:
                // Добавление книги
                break;
            case 2:
                // Удаление книги
                break;
            default:
                System.out.println("не корректный выбор\n");
        }
    }


    private void showUserMenu() {
        while (true) {
            System.out.println("Меню пользователя");
            System.out.println("1 -> Регистрация нового пользователя");
            System.out.println("2 -> Авторизация");
            System.out.println("3 -> Logout");
            System.out.println("0 -> Возврат в предыдущее меню");

            System.out.println("\nСделайте выбор пункта:");

            int input = scanner.nextInt();
            scanner.nextLine();
            if (input == 0) break;

            handleUserMenuChoice(input);
        }
    }


    private void handleUserMenuChoice(int input) {
        switch (input) {
            case 1:
                //Регистрация нового пользователя");
                //TODO Создать в UserService метод "register"

                waitRead();
                break;

            case 2:
                System.out.println("Авторизация пользователя");
                System.out.println("Введите username:");
                String name = scanner.nextLine();
                System.out.println("Введите email:");
                String email = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();

                //TODO Создать в UserService метод "public boolean login"
                /*
                boolean isLogged = userService.login(name, password)
                if (registered) {
                    System.out.println("Вы успешно авторизовались в системе");
                    } else {
                    System.out.println("Не верно введены данные");
                }

                */
                waitRead();
                break;

            case 3:
                //TODO userService.logout
                waitRead();
                break;

            default:
                System.out.println("не корректный ввод\n");

        }
    }
    private void waitRead () {
        System.out.println("\nДля продолжения нажмите Enter ");
        scanner.nextLine();

    }
}
