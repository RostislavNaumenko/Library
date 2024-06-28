package model;

public class User {
    private final int userId;
    private String name;
    private String email;
    private String password;
    private Role role;

    public User(int userId, String name, String email, String password) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.role = Role.USER;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(Role role, User activeUser) {
        if (activeUser.getRole() == Role.ADMIN) {
            this.role = role;
        } else {
            throw new SecurityException("В доступе отказано");
        }
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
