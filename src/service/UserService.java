package service;


import model.User;
import repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    private User activeUser;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User registerUser(String name, String email,String password){
        //TODO Добавить валидация email, password, name
        // name:
        // 1.должно начинаться с большой буквы
        // 2.не должно иметь цифр и специальных знаков

        //TODO isEmailExist


        User user = userRepository.addUser(name, email, password);

        return user;
    }

}
