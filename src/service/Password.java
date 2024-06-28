package service;

public class Password {

    public void validatePassword(){
        // 2.Валидация password:
        //   String password = "MySecurePassword123!";
        boolean isValidPassword = password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,}$");
        //Это регулярное выражение проверяет, что пароль содержит
        // как минимум одну букву, одну цифру и один специальный символ, и имеет длину не менее 8 символов
    }
}