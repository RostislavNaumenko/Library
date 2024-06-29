package service;


import model.Role;
import model.User;
import repository.UserRepository;
import util.MyList;

import java.util.regex.Pattern;

public class UserService {

    private final UserRepository userRepository;
    private User activeUser;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Добавление пользователя
    public User registerUser(String name, String email, String password) {
        //TODO (Rostyslav) Добавить валидация email, password, name
        // name:
        // 1.должно начинаться с большой буквы
        // 2.не должно иметь цифр и специальных знаков

        if (userRepository.isEmailExists(email)) return null;
        User user = userRepository.addUser(name, email, password);
        return user;
    }

    // аутентификации пользователя
    public boolean authenticate(String email, String password) {
        User user = userRepository.getUserByEmail(email);
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
    public boolean logout() {
        if (activeUser == null) return false;
        activeUser = null;
        return true;
    }
    // Admin methods (get and set)

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public User getUserById (int id){
        if(id > userRepository.getAllUsers().size()) return null;

        return userRepository.getUserById(id);
    }

    public MyList<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User setUserRole(int id, Role role){
        if(id > userRepository.getAllUsers().size()) return null;

        return userRepository.setUserRole(id, role);
    }

    //Валидация email
    //TODO (Rostyslav)
    private static boolean validateEmail(String email) {
        String regexPattern = "^(?=. {1,64}@) [A-Za-z0-9_-]+ (\\\\. [A-Za-z0-9_-]+)*@ [^-] [A-Za-z0-9-]+ (\\\\. [A-Za-z0-9-]+)* (\\\\. [A-Za-z] {2,})$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

}