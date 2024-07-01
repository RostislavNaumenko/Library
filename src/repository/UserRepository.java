package repository;

import model.Role;
import model.User;
import util.MagicList;
import util.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

    private final MyList<User> users;
    private final AtomicInteger currentId = new AtomicInteger(1);
    private User activeUser;


    public UserRepository() {
        this.users = new MagicList<>();
    }

    public User addUser(String name, String email,String password){
        User user = new User(currentId.getAndIncrement(), name, email, password);
        users.add(user);

        return user;

    }

    //GET

    public User getUserByEmail(String email){
        for(User user : users){
            if (user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;
    }


    public User getUserById (int id){
        for (User user : users) {
            if(user.getUserId() == id) return user;
        }
        return null;
    }

    public MyList<User> getAllUsers(){
        MyList<User> allUsers = new MagicList<>();
        for(User user : users){
            allUsers.add(user);
        }

        return allUsers;
    }

    //Проверка на существование email
    public boolean isEmailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    //Set
    public User setUserRole(int id, Role role){
       User user = getUserById(id);
       if(user == null) return null;
       user.setRole(role);

       return user;
    }


}
