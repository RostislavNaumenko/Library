package view;

import java.util.Scanner;

public class UserMenu {

    public UserMenu() {

    }

    private final Scanner scanner = new Scanner(System.in);

    public void showUserMenu() {
        while (true) {
            System.out.println("Меню пользователя");
            System.out.println("1 -> Регистрация нового пользователя");
            System.out.println("2 -> Авторизация");
            System.out.println("3 -> Logout");
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
                //Регистрация нового пользователя");
                //TODO Создать в UserService метод "register"
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

                break;

            case 3:
                //TODO userService.logout

                break;

            default:
                System.out.println("не корректный ввод\n");

        }
    }

}
