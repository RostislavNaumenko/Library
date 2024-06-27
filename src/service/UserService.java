package service;


import model.Book;
import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;
    private User activeUser;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User registerUser(String name, String email,String password){
        //TODO (Alla) Добавить валидация email, password, name
        // name:
        // 1.должно начинаться с большой буквы
        // 2.не должно иметь цифр и специальных знаков

        //TODO(Rostyslav)isEmailExist


        User user = userRepository.addUser(name, email, password);

        return user;
    }


        // 1.Валидация email:
        public class EmailValidation {
            public static boolean isValidEmailAddress(String email) {
                boolean result = true;
                try {
                    Email emailAddr = new emailAddr(email);
                    emailAddr.validate();
                } catch (AddressException ex) {
                    result = false;
                }
                return result;
            }
        }
        // 2.Валидация password:
           String password = "MySecurePassword123!";

        boolean isValidPassword = password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,}$");
        //Это регулярное выражение проверяет, что пароль содержит
        // как минимум одну букву, одну цифру и один специальный символ, и имеет длину не менее 8 символов


        // 3.Валидация name:
          String name = "John"; // Пример имени
        boolean isValidName = name.matches("^[A-Z][a-zA-Z]*$");
        // Это регулярное выражение проверяет, что имя начинается с большой буквы и содержит только буквы

    }



    // Ваш класс Book должен иметь методы getAuthor() и isBorrowed()

    // Пример использования:
    /*
    public static void main(String[] args) {
        Library library = new Library();
        // Заполните список книг
        // ...

        String desiredAuthor = "J.K. Rowling";
        List<Book> availableBooksByAuthor = library.getAllAvailableBooksByAuthor(desiredAuthor);
        if (availableBooksByAuthor.isEmpty()) {
            System.out.println("Нет доступных книг автора " + desiredAuthor);
        } else {
            System.out.println("Доступные книги автора " + desiredAuthor + ":");
            for (Book book : availableBooksByAuthor) {
                System.out.println(book.getTitle());
            }
        }
    }
}
*/