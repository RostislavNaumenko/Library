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

    public User addUser(String name, String email,String password){
        User user = new User(currentId.getAndIncrement(), name, email, password);
        users.add(user);

        return user;

    }


}
