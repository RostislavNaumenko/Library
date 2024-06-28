package repository;

import model.Book;
import model.BookStorage;
import model.User;
import util.MyList;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

    private final MyList<User> users;

    private final AtomicInteger currentId = new AtomicInteger(1);


    public UserRepository(MyList<User> users) {
        this.users = users;
    }

    public User addUser(String name, String email,String password){
        User user = new User(currentId.getAndIncrement(), name, email, password);
        users.add(user);

        return user;

    }

   // BookStorage bookStorage= new BookStorage();
    // Добавьте книги в библиотеку

    String desiredAuthor = "Петров В.В."; // Заменить на нужного автора
    ArrayList<Book> booksByAuthor =BookStorage.getBooksByAuthor (desiredAuthor);

    String isEmpty= null;
// TODO здесь лезет ошибка, помогите решить
        if (booksByAuthor.isEmpty()) { // добавляла .isTaken результат тот же= красный
        System.out.println("Нет книг автора " + desiredAuthor);
    } else {
        System.out.println("Книги автора " + desiredAuthor + ":");
        for (Book book : booksByAuthor) {
            System.out.println(book.getTitle()); // Заменить на нужное поле (название книги)
        }
    }
}
