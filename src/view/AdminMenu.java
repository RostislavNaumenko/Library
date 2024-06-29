package view;

import java.util.Scanner;

public class AdminMenu {


    private final Scanner scanner = new Scanner(System.in);

    public AdminMenu() {

    }

    public void showAdminMenu() {
        while (true) {
            System.out.println("Меню Администратора");
            System.out.println("1 -> Добавление книги");
            System.out.println("2 -> Удаление книги");
            System.out.println("0 -> Возврат в предыдущее меню");

            System.out.println("\nСделайте выбор пункта:");

            int control = scanner.nextInt();
            scanner.nextLine();
            if (control == 0)
                break;

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
}
