package service;

// 1.Валидация email:


//метод isValidEmailAddress должен корректно проверять валидность email-адреса.
public class EmailValidation {
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress address = new InternetAddress(email);
            address.validate();
        } catch (AddressException) {

            result = false;
            System.out.println("Адрес не найден");
        } // TODO здесь лезет ошибка, помогите решить
        return result;
    }
}