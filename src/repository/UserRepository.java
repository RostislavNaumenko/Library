package repository;

import model.User;
import util.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

    private final MyList<User> users;
    private final AtomicInteger currentId = new AtomicInteger(1);

    public UserRepository(MyList<User> users) {
        this.users = users;
    }

        // поиска по email
    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
}