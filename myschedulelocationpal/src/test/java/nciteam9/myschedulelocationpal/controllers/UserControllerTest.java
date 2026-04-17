package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.dtos.LastLocationDto;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void upDateUserLastKnowLocation() {

        User user = User.builder()
                .userID(1)
                .build();

        when(userRepository.findByUserID(user.getUserID())).thenReturn(user);

        LastLocationDto lastLocationDto = new LastLocationDto();
        lastLocationDto.setUserId(1);
        lastLocationDto.setLastLatitude(5.5);
        lastLocationDto.setLastLongitude(6.6);

        userController.setLastLocation(lastLocationDto);

        assertEquals(5.5, user.getLastLatitude());
        assertEquals(6.6, user.getLastLongitude());
    }

}