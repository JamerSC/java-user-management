import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
}
