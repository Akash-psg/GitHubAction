package quizapp.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import quizapp.demo.Models.User;
import quizapp.demo.Repositories.UserRepository;
import quizapp.demo.Services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser() {

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setEmail("testEmail");

        when(userRepository.save(user)).thenReturn(user);


        User registeredUser = userService.registerUser(user);


        assertEquals(user.getUsername(), registeredUser.getUsername());
        assertEquals(user.getPassword(), registeredUser.getPassword());
        assertEquals(user.getEmail(), registeredUser.getEmail());
    }
}
