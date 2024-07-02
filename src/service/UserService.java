package service;


import model.Role;
import model.User;
import repository.UserRepository;
import util.MyList;

public class UserService {

    private final UserRepository userRepository;
    private User activeUser;


    public UserService() {
        this.userRepository = new UserRepository();
    }

    //Добавление пользователя
    public User registerUser(String name, String email, String password) {
        if ( !isNameValid(name) || !isEmailValid(email) || userRepository.isEmailExists(email) || !isPasswordValid(password) ){
            return null;
        }
        User user = userRepository.addUser(name, email, password);
        activeUser = user;
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
    public void logout() {
        if (activeUser == null) return;
        activeUser = null;
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

    //Валидация email
    private boolean isEmailValid(String email) {

        // 1. должна присутствовать @ и только одна
        int indexAt = email.indexOf('@');
        if (indexAt == -1 || indexAt != email.lastIndexOf('@')) return false;

        // 2. Точка после собаки
        if (email.indexOf('.', indexAt) == -1) return false;

        //3. после последней точки 2 или более символов
        if (email.lastIndexOf('.') >= email.length() - 2) return false;

        //4. английский алфавит, цифры, '_', '-', '.', '@'

        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);

            if (!(Character.isAlphabetic(ch)
                    || Character.isDigit(ch)
                    || ch == '_'
                    || ch == '-'
                    || ch == '.'
                    || ch == '@')) {
                return false;
            }
        }

        // 5. до собаки должен быть мин 1 символ
        // ???
        if (indexAt == 0) return false;

        if (!Character.isAlphabetic(email.charAt(0)))  return false;

        return true;
    }

    private boolean isPasswordValid(String password) {
        if (password == null || password.length() < 8) return false;

        boolean isDigit = false;
        boolean isLowerCase = false;
        boolean isUpperCase = false;
        boolean isSpecialSymbol = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                isDigit = true;
            }
            if (Character.isLowerCase(ch)) {
                isLowerCase = true;
            }
            if (Character.isUpperCase(ch)) {
                isUpperCase = true;
            }
            if ("!%$@&*()[]".indexOf(ch) >= 0) {
                isSpecialSymbol = true;
            }
        }
        return  isDigit && isLowerCase && isUpperCase && isSpecialSymbol;

    }

    private boolean isNameValid(String name) {
        if(name == null || name.length() < 3 || name.length() > 50) return false;

        boolean isFirstLetterUpperCase = Character.isUpperCase(name.charAt(0));
        boolean noDigit = true;
        boolean noSpecialSymbol = true;
        boolean validCharacters = true;

        for (char ch : name.toCharArray()) {
            if (Character.isDigit(ch)) {
                noDigit = false;
            }
            if ("!%$@&*()[]".indexOf(ch) >= 0) {
                noSpecialSymbol = false;
            }
            if (!Character.isLetter(ch) && ch != ' ') {
                validCharacters = false;
            }

        }

        boolean noLeadingOrTrailingSpaces = name.charAt(0) != ' ' && name.charAt(name.length() - 1) != ' ';
        boolean noConsecutiveSpaces = !name.contains("  ");
        return isFirstLetterUpperCase && noDigit && noSpecialSymbol && validCharacters && noLeadingOrTrailingSpaces && noConsecutiveSpaces;

    }


}