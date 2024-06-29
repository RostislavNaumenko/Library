package view;

import java.util.Scanner;

public class BookMenu  {


    private final Scanner scanner = new Scanner(System.in);

    public BookMenu() {

    }

    public void showBookMenu() {
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
            if (actions == 0)
                break;

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
}
