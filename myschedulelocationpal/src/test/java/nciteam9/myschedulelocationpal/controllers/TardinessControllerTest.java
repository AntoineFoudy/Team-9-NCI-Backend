package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TardinessControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TardinessController tardinessController;

    @Test
    public void userHasATardinessHistory() {

        User user = User.builder()
                .userID(1)
                .onTime(6)
                .late(13)
                .build();

        when(userRepository.findByUserID(user.getUserID())).thenReturn(user);

        ResponseEntity<?> response = tardinessController.getTardiness(user.getUserID());


        ArrayList<String> result = (ArrayList<String>) response.getBody();

        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("6");
        expectedResult.add("13");



        assertEquals(expectedResult, result);
    }

    @Test
    public void userHasNoTardinessHistory() {

        User user = User.builder()
                .userID(1)
                .onTime(0)
                .late(0)
                .build();

        when(userRepository.findByUserID(user.getUserID())).thenReturn(user);

        ResponseEntity<?> response = tardinessController.getTardiness(user.getUserID());


        ArrayList<String> result = (ArrayList<String>) response.getBody();

        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("0");
        expectedResult.add("0");



        assertEquals(expectedResult, result);
    }

}