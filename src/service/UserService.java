package service;


import model.User;
import repository.UserRepository;

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
        User user = registerUser(name, email, password);
        return user;
    }

    // аутентификации пользователя
    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            this.activeUser = user;
            return true;
        }
        return false;
    }
    // получить активного пользователя
    public User getActiveUser() {
        return activeUser;
    }

    // для выхода пользователя
    public void logout() {
        this.activeUser = null;
    }
}