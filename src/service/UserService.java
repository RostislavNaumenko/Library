package service;


import model.User;
import modelLayer.Role;
import repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    private User activeUser;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User registerUser(String name, String email, String password, Role role) {
        //TODO (Alla) Добавить валидация email, password, name
        // name:
        // 1.должно начинаться с большой буквы
        // 2.не должно иметь цифр и специальных знаков

        //TODO(Rostyslav)isEmailExist


        User user = userRepository.addUser(name, email, password, role);

        return user;
    }

}
