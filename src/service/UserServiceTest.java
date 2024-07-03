package service;

import model.Role;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;


class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        userService = new UserService();
    }

    @Test
    public void testRegisterUser_ValidData() {
        User user = userService.registerUser("Max", "max@gmail.com", "Password1!");

        assertNotNull(user);
        assertEquals("Max", user.getName());
        assertEquals("max@gmail.com", user.getEmail());
    }
    @Test
    public void testRegisterUser_InvalidEmail() {
        User user = userService.registerUser("Max", "max-gmail.com", "Password1!");
        assertNull(user);
    }

    @Test
    public void testAuthenticate_ValidCredentials() {
        userService.registerUser("Max", "max@gmail.com", "Password1!");
        boolean isAuthenticated = userService.authenticate("max@gmail.com", "Password1!");
        assertTrue(isAuthenticated);
    }

    @Test
    public void testAuthenticate_InvalidCredentials() {
        userService.registerUser("Max", "max@gmail.com", "Password1!");
        boolean isAuthenticated = userService.authenticate("max@gmail.com", "wrongpassword");
        assertFalse(isAuthenticated);
    }

    @Test
    public void testGetActiveUser() {
        userService.registerUser("Max", "max@gmail.com", "Password1!");
        userService.authenticate("max@gmail.com", "Password1!");

        User activeUser = userService.getActiveUser();
        assertNotNull(activeUser);
        assertEquals("Max", activeUser.getName());
    }

    @Test
    public void testLogout() {
        userService.registerUser("Max", "max@gmail.com", "Password1!");
        userService.authenticate("max@gmail.com", "Password1!");

        userService.logout();
        assertNull(userService.getActiveUser());
    }
    @Disabled
    @Test
    public void testGetUserByEmail_AsAdmin() {
        User adminUser = new User(123456, "admin", "admin@gmail.com", "AdminPass1!");
        userRepository.addUser(adminUser.getName(), adminUser.getEmail(), adminUser.getPassword());

        userService.authenticate("admin@gmail.com", "AdminPass1!");

        User result = userService.getUserByEmail("admin@gmail.com");
        assertNotNull(result);
        assertEquals("Admin", result.getName());
    }
    @Disabled
    @Test
    public void testSetUserRole_AsAdmin() {
        User adminUser = new User(123456, "admin", "admin@gmail.com", "AdminPass1!");
        userRepository.addUser(adminUser.getName(), adminUser.getEmail(), adminUser.getPassword());

        userService.authenticate("admin@gmail.com", "AdminPass1!");
        User user = userService.registerUser("Max", "max@gmail.com", "Password1!");

        User result = userService.setUserRole(Integer.parseInt(String.valueOf(user.getUserId())), Role.ADMIN);
        assertNotNull(result);
        assertEquals(Role.ADMIN, result.getRole());
    }

    @Test
    public void testSetUserRole_NotAdmin() {
        User user = userService.registerUser("Max", "max@gmail.com", "Password1!");

        userService.authenticate("max@gmail.com", "Password1!");
        User result = userService.setUserRole(user.getUserId(), Role.ADMIN);
        assertNull(result);
    }
}
