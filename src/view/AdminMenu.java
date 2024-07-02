package view;

import model.Book;
import model.Role;
import model.User;
import service.BookService;
import service.UserService;

import java.util.Scanner;

public class AdminMenu {

    private final BookService bookService;
    private final UserService userService;

    private final Scanner scanner = new Scanner(System.in);

    public AdminMenu(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    public void showAdminMenu() {
        while (true) {
            System.out.println("МЕНЮ АДМИНИСТРАТОРА");
            System.out.println("1 -> Добавление книги");
            System.out.println("2 -> Удаление книги");
            System.out.println("3 -> Все книги");
            System.out.println("4 -> Сменить роль пользователю");
            System.out.println("5 -> Все пользователи");
            System.out.println("0 -> Возврат в предыдущее меню");
            System.out.println("\nСделайте выбор пункта:");
            int control = scanner.nextInt();
            scanner.nextLine();
            switch (control){
                case 0:
                    break;
                case 1:
                case 2:
                    AdminMenuWithBooks(control);
                case 3:
                    showBooks() ;
                    waitRead();
                    break;
                case 4:
                    AdminMenuWithUsers();
                case 5:
                    showUsers();
                    waitRead();
                    break;
                default:
                    System.out.println("не корректный выбор\n");

            }

        }

    }

    private void AdminMenuWithBooks(int control) {
        switch (control) {
            case 1:
                // Добавление книги
                System.out.println("\nДОБАВЛЕНИЕ КНИГИ");
                System.out.println("Введите название книги: ");
                String title = scanner.nextLine();
                System.out.println("Введите автора книги: ");
                String author = scanner.nextLine();
                Book book = bookService.addBook(title, author);
                if (book == null) {
                    System.out.println("Неправильное название или автор книги");
                    waitRead();
                    break;
                }
                System.out.println(book);
                waitRead();
                break;
            case 2:
                // Удаление книги
                while (true) {
                    System.out.println("\nУДАЛЕНИЕ КНИГИ");
                    System.out.println("1 -> Удаление книги по id");
                    System.out.println("2 -> Удаление книги по названию и автору");
                    System.out.println("0 -> Возврат в предыдущее меню");
                    System.out.println("\nСделайте выбор пункта:");
                    int controlDelete = scanner.nextInt();
                    scanner.nextLine();
                    if (controlDelete == 0) break;

                    AdminMenuWithDeletingBooks(controlDelete);
                }
            default:
                System.out.println("не корректный выбор\n");
        }
    }

    private void AdminMenuWithDeletingBooks(int controlDelete) {
        showBooks();
        switch (controlDelete) {
            case 1:
                System.out.println("Введите id книги:");
                int id = scanner.nextInt();
                scanner.nextLine();
                if (bookService.removeBook(id) == null) {
                    System.out.println("Такой книги не найдено");
                    waitRead();
                    break;
                }
                System.out.printf("Книга с id = %d была удалена\n", id);
                bookService.removeBook(id);
                showBooks();
                waitRead();
                break;
            case 2:
                System.out.println("Введите название книги:");
                String title = scanner.nextLine();
                System.out.println("Введите автора книги: ");
                String author = scanner.nextLine();
                if (bookService.removeBook(title, author) == null) {
                    System.out.println("Такой книги не найдено");
                    waitRead();
                    break;
                }
                System.out.println("Книга была удалена: ");
                bookService.removeBook(title, author);
                showBooks();
                waitRead();
                break;

            default:
                System.out.println("не корректный выбор\n");

        }
    }

    private void AdminMenuWithUsers() {
        while (true) {
            System.out.println("1 -> Изменить роль пользователя по email");
            System.out.println("2 -> Изменить роль пользователя по id");
            System.out.println("0 -> Возврат в предыдущее меню");
            System.out.println("\nСделайте выбор пункта:");
            int control = scanner.nextInt();
            scanner.nextLine();
            if (control == 0) break;

            AdminMenuChangeUserRole(control);

        }
    }

    private void AdminMenuChangeUserRole(int control){
        showUsers();
        switch (control) {
            case 1:
                System.out.println("Введите email пользователя: ");
                String email = scanner.nextLine();
                User user = userService.getUserByEmail(email);
                if(user == null) {
                    System.out.println("Такой пользователь не найден");
                    break;
                }
                System.out.println("Ведите новую роль пользователя: ");;
                String roleInput = scanner.nextLine().toUpperCase();
                Role newRole;
                try {
                    newRole = Role.valueOf(roleInput);
                } catch (IllegalArgumentException e) {
                    System.out.println("Некорректная роль: " + roleInput);
                    break;
                }
                user.setRole(newRole);
                System.out.println("Роль пользователя успешно обновлена.");
                break;
            case 2:
                System.out.println("Введите id пользователя: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                User user1 = userService.getUserById(id);
                if(user1 == null){
                    System.out.println("Такой пользователь не найден");
                    break;
                }
                System.out.println("Ведите новую роль пользователя: ");;
                String roleInput1 = scanner.nextLine().toUpperCase();
                Role newRole1;
                try {
                    newRole = Role.valueOf(roleInput1);
                } catch (IllegalArgumentException e) {
                    System.out.println("Некорректная роль: " + roleInput1);
                    break;
                }
                user1.setRole(newRole);
                System.out.println("Роль пользователя успешно обновлена.");
                break;
            default:
                System.out.println("не корректный выбор\n");
        }
    }

    public void showUsers(){
        System.out.println(userService.getAllUsers());
    }

    public void showBooks() {
        System.out.println(bookService.getAllBooks());
    }

    public void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter ");
        scanner.nextLine();
    }
}
