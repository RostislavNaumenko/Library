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

    private User registerUser(String name, String email, String password) {
        //TODO (Alla) Добавить валидация email, password, name
        // name:
        // 1.должно начинаться с большой буквы
        // 2.не должно иметь цифр и специальных знаков

        //TODO(Rostyslav)isEmailExist


        User user = userRepository.addUser(name, email, password);

        return user;
    }
}









    // Ваш класс Book должен иметь методы getAuthor() и isBorrowed()

    // Пример использования:
    /*
    public static void main(String[] args) {
        Library library = new Library();
        // Заполните список книг
        // ...


            }
        }
    }
}
*/