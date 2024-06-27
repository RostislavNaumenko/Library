package model;

import javax.management.relation.Role;

public class User {
    private final int userId;
    private String name;
    private String email;
    private String password;
    private Role role;
    //TODO (Rostyslav) Добавить Роли (админ, читатель)


    public User(int userId, String name, String email, String password, Role role) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
