package model;

import modelLayer.Role;

public class Admin extends User {
    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password, Role.ADMIN);
    }

    public void manageUsers() {

    }

    public void generateReports() {

    }
}