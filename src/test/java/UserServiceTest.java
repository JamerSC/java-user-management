import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserDAO userDAO;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDAO = mock(UserDAO.class);
        userService = new UserService(userDAO);
    }

//    1. Use a Testing Framework
//    Use a popular testing framework like JUnit (e.g., JUnit 5) for writing and running tests.
//    Add the JUnit dependency to your pom.xml:
//    2. Follow the Arrange-Act-Assert Pattern
//    Structure your tests into three sections:
//            Arrange: Set up the test data and environment.
//            Act: Call the method under test.
//            Assert: Verify the results.
//    3. Write Tests for Each Public Method
//    Test all public methods in your classes, especially in UserService and UserDAO.
//    4. Mock Dependencies
//    Use mocking frameworks like Mockito to mock dependencies (e.g., UserDAO in UserService).
//    5. Use Meaningful Test Names
//    Use descriptive names for test methods to indicate what is being tested and the expected outcome.
//    6. Test Edge Cases
//    Write tests for edge cases, such as null inputs, empty strings, and invalid data.


    @Test
    void createUser_ShouldSaveUser_WhenValidInput() {
        // Arrange
        String name = "Wolfgang Amadeus Mozart";
        String email = "wolgang@mail.com";

        // Act
        userService.createUser(name, email);

        // Assert
        verify(userDAO, times(1))
                .save(any(User.class)); // Verify save() was called once
    }

    @Test
    void createUser_ShouldThrowException_WhenNameIsBlank() {
        // Arrange
        String name = " ";
        String email = "example@example.com";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(name, email);
        });

//        try {
//            userService.createUser(name, email);
//        } catch (IllegalArgumentException e) {
//            assert(e.getMessage().equals("Name cannot be null or blank"));
//        }

        assertEquals("Name cannot be null or blank", exception.getMessage());
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        // Arrange
        List<User> mockUsers = Arrays.asList(
                new User(1, "John Doe", "john.doe@example.com"),
                new User(2, "Jane Doe", "jane.doe@example.com")
        );
        when(userDAO.findAll()).thenReturn(mockUsers);

        // Act
        List<User> users = userService.getAllUsers();

        // Assert
        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        verify(userDAO, times(1)).findAll();
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        int userId = 1;
        User mockUser = new User(userId, "John Doe", "john.doe@example.com");
        when(userDAO.findById(userId)).thenReturn(mockUser);

        // Act
        User user = userService.getUserById(userId);

        // Assert
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        verify(userDAO, times(1)).findById(userId);
    }

    @Test
    void getUserById_ShouldReturnNull_WhenUserDoesNotExist() {
        // Arrange
        int userId = 1;
        when(userDAO.findById(userId)).thenReturn(null);

        // Act
        User user = userService.getUserById(userId);

        // Assert
        assertNull(user);
        verify(userDAO, times(1)).findById(userId);
    }

    @Test
    void updateUser_ShouldUpdateUser_WhenValidInput() {
        // Arrange
        int userId = 1;
        String name = "Updated Name";
        String email = "updated.email@example.com";

        // Act
        userService.updateUser(userId, name, email);

        // Assert
        verify(userDAO, times(1)).update(any(User.class));
    }

    @Test
    void updateUser_ShouldThrowException_WhenNameIsBlank() {
        // Arrange
        int userId = 1;
        String name = " ";
        String email = "updated.email@example.com";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.updateUser(userId, name, email);
        });

        assertEquals("Name cannot be null or blank", exception.getMessage());
    }

    @Test
    void deleteUserById_ShouldDeleteUser_WhenUserExists() {
        // Arrange
        int userId = 1;

        // Act
        userService.deleteUserById(userId);

        // Assert
        verify(userDAO, times(1)).delete(userId);
    }
}
