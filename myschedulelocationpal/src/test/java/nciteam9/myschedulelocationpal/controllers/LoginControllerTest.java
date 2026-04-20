package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.dtos.LoginDto;
import nciteam9.myschedulelocationpal.dtos.RequestLoginDto;
import nciteam9.myschedulelocationpal.entities.Login;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void credentialsAreCorrect() {
        User user = User.builder()
                .userID(1)
                .firstName("Antoine")
                .lastName("Foudy")
                .lastLatitude(3.33)
                .lastLongitude(4.44)
                .onTime(0)
                .late(0)
                .build();

        Login login = Login.builder()
                .loginID(1)
                .userID(1)
                .user(user)
                .email("foudyantoine@gmail.com")
                .password("password")
                .build();

        RequestLoginDto requestLoginDto = new RequestLoginDto();
        requestLoginDto.setLoginEmail(login.getEmail());
        requestLoginDto.setLoginPassword(login.getPassword());

        when(loginRepository.findByEmail(requestLoginDto.getLoginEmail())).thenReturn(login);

        ResponseEntity<?> response = loginController.getLogin(requestLoginDto);

        LoginDto result = (LoginDto) response.getBody();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, result.getUserID());
        assertEquals("Antoine", result.getFirstName());
        assertEquals("Foudy", result.getLastName());

    }

    @Test
    public void emailIsIncorrect() {
        User user = User.builder()
                .userID(1)
                .firstName("Antoine")
                .lastName("Foudy")
                .lastLatitude(3.33)
                .lastLongitude(4.44)
                .onTime(0)
                .late(0)
                .build();

        Login login = Login.builder()
                .loginID(1)
                .userID(1)
                .user(user)
                .email("foudyantoine@gmail.com")
                .password("password")
                .build();

        RequestLoginDto requestLoginDto = new RequestLoginDto();
        requestLoginDto.setLoginEmail("af@gmail.com");
        requestLoginDto.setLoginPassword(login.getPassword());

        when(loginRepository.findByEmail(requestLoginDto.getLoginEmail())).thenReturn(null);

        ResponseEntity<?> response = loginController.getLogin(requestLoginDto);

        LoginDto result = (LoginDto) response.getBody();

        assertEquals(401, response.getStatusCode().value());
    }

    @Test
    public void passwordIsIncorrect() {
        User user = User.builder()
                .userID(1)
                .firstName("Antoine")
                .lastName("Foudy")
                .lastLatitude(3.33)
                .lastLongitude(4.44)
                .onTime(0)
                .late(0)
                .build();

        Login login = Login.builder()
                .loginID(1)
                .userID(1)
                .user(user)
                .email("foudyantoine@gmail.com")
                .password("password")
                .build();

        RequestLoginDto requestLoginDto = new RequestLoginDto();
        requestLoginDto.setLoginEmail(login.getEmail());
        requestLoginDto.setLoginPassword("qwerty");

        when(loginRepository.findByEmail(requestLoginDto.getLoginEmail())).thenReturn(login);

        ResponseEntity<?> response = loginController.getLogin(requestLoginDto);

        LoginDto result = (LoginDto) response.getBody();

        assertEquals(401, response.getStatusCode().value());
    }

}