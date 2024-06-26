package model;

import javax.management.relation.Role;

public class User {
    private final int userId;
    private String name;
    private String email;
    private String password;

    //TODO Добавить Роли (админ, читатель)


    public User(int userId, String name, String email, String password) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.userId = userId;
    }


    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
