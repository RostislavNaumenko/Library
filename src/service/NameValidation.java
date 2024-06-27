package service;

public class NameValidation {

    // 3.Валидация name:
    String name = "John"; // Пример имени
    boolean isValidName = name.matches("^[A-Z][a-zA-Z]*$");
    // Это регулярное выражение проверяет, что имя начинается с большой буквы и содержит только буквы

}

