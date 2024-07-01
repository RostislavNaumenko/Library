package service;


import model.Role;
import model.User;
import repository.UserRepository;
import util.MyList;

import java.util.regex.Pattern;

public class UserService {

    private final UserRepository userRepository;
    private User activeUser;


    public UserService() {
        this.userRepository = new UserRepository();
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
        if(activeUser.getRole().equals(Role.ADMIN)){
            return userRepository.getUserByEmail(email);
        }
        return null;

    }

    public User getUserById (int id){
        if(!activeUser.getRole().equals(Role.ADMIN) || id > userRepository.getAllUsers().size()) return null;
        return userRepository.getUserById(id);
    }

    public MyList<User> getAllUsers(){
        if(activeUser.getRole().equals(Role.ADMIN)){
            return userRepository.getAllUsers();
        }
        return null;
    }

    public User setUserRole(int id, Role role){
        if(id > userRepository.getAllUsers().size() || !activeUser.getRole().equals(Role.ADMIN)) return null;

        return userRepository.setUserRole(id, role);
    }



}