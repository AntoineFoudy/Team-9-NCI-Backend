package nciteam9.myschedulelocationpal.service;

import nciteam9.myschedulelocationpal.entities.Login;
import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NextEventTest {

    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private LoginRepository loginRepository;
    @Mock
    private SendEmail sendEmail;

    @InjectMocks
    private NextEvent nextEvent;


    @Test
    void eventIsWithin20MinAndEmailIsSent() throws Exception {

        User user = User.builder()
                .userID(1)
                .firstName("Antoine")
                .lastName("Foudy")
                .lastLatitude(3.33)
                .lastLongitude(4.44)
                .onTime(0)
                .late(0)
                .build();

        Instant now = Instant.now();
        Instant eventTime = now.plus(Duration.ofMinutes(10));

        Schedule schedule = Schedule.builder()
                .scheduleId(1)
                .userId(1)
                .dateTime(eventTime)
                .latitude(3.33)
                .longitude(4.44)
                .description("Test")
                .build();

        Login login = Login.builder()
                .loginID(1)
                .userID(1)
                .email("foudyantoine@gmail.com")
                .password("123")
                .build();

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(scheduleRepository.findByuserId(1)).thenReturn(List.of(schedule));
        when(loginRepository.findByUserID(1)).thenReturn(login);

        nextEvent.controlFlow();

        verify(sendEmail).sendMessage(
                eq("foudyantoine@gmail.com"),
                eq("Antoine You have an event in 20 Minutes!"),
                anyString()
        );

    }
}