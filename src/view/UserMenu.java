package view;

import service.UserService;

import java.util.Scanner;

public class UserMenu  {
    private final UserService userService;

    public UserMenu(UserService userService) {
        this.userService = userService;
    }

    private final Scanner scanner = new Scanner(System.in);

    public void showUserMenu() {
        while (true) {
            System.out.println("Меню пользователя");
            System.out.println("1 -> Информация о пользователе");;
            System.out.println("0 -> Возврат в предыдущее меню");

            System.out.println("\nСделайте выбор пункта:");

            int input = scanner.nextInt();
            scanner.nextLine();
            if (input == 0)
                break;

            handleUserMenuChoice(input);
        }
    }

    private void handleUserMenuChoice(int input) {
        switch (input) {
            case 1:
                System.out.println("\n Ваша информация: \n");
                System.out.println(userService.getActiveUser());
                waitRead();
                break;

            default:
                System.out.println("не корректный ввод\n");

        }
    }

    public void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter ");
        scanner.nextLine();
    }

}
