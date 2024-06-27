package repository;

import model.User;
import modelLayer.Role;
import util.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

    private final MyList<User> users;
    private final AtomicInteger currentId = new AtomicInteger(1);


    public UserRepository(MyList<User> users) {
        this.users = users;
    }

    public User addUser(String name, String email, String password, Role role){
        User user;
        if (role == Role.ADMIN) {
            user = new Admin(currentId.getAndIncrement(), name, email, password);
        } else {
            user = new User(currentId.getAndIncrement(), name, email, password, role);
        }
        users.add(user);
        return user;
    }
}