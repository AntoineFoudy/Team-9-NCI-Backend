package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.dtos.LoginDto;
import nciteam9.myschedulelocationpal.dtos.SignupDto;
import nciteam9.myschedulelocationpal.entities.Login;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignupControllerTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private SignupController signupController;

    @Test
    public void newUserCreated() {

        SignupDto signupDto = new SignupDto();
        signupDto.setFirstName("Antoine");
        signupDto.setLastName("Foudy");
        signupDto.setEmail("foudyantoine@gmail.com");
        signupDto.setPassword("password");

        when(loginRepository.findByEmail(signupDto.getEmail())).thenReturn(null);

        User user = User.builder()
                .userID(0)
                .firstName("Antoine")
                .lastName("Foudy")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        Login login = Login.builder()
                .userID(0)
                .email("foudyantoine@gmail.com")
                .password("password")
                .build();

        when(loginRepository.save(any(Login.class))).thenReturn(login);

        ResponseEntity<?> response = signupController.userSignup(signupDto);

        LoginDto loginDto = (LoginDto) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, loginDto.getUserID());
        assertEquals("Antoine", loginDto.getFirstName());
        assertEquals("Foudy", loginDto.getLastName());

    }

    @Test
    public void emailAlreadyExitsUserNotCreated() {

        Login existingUser = Login.builder()
                .userID(0)
                .email("foudyantoine@gmail.com")
                .build();

        SignupDto signupDto = new SignupDto();
        signupDto.setFirstName("Antoine");
        signupDto.setLastName("Foudy");
        signupDto.setEmail("foudyantoine@gmail.com");
        signupDto.setPassword("password");

        when(loginRepository.findByEmail(signupDto.getEmail())).thenReturn(existingUser);

        ResponseEntity<?> response = signupController.userSignup(signupDto);

        LoginDto loginDto = (LoginDto) response.getBody();

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        verify(userRepository, never()).save(any(User.class));
        verify(loginRepository, never()).save(any(Login.class));


    }
}