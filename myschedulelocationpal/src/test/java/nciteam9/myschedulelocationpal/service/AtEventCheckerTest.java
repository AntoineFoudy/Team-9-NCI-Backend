package nciteam9.myschedulelocationpal.service;

import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtEventCheckerTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AtEventChecker atEventChecker;

    @Test
    public void userIsWithin100m() {
        Instant now = Instant.now();

        Schedule schedule = Schedule.builder()
                .userId(1)
                .dateTime(now)
                .latitude(53.3327941)
                .longitude(6.2804578)
                .build();

        User user = User.builder()
                .userID(1)
                .lastLatitude(53.3327941)
                .lastLongitude(6.2804578)
                .onTime(0)
                .late(0)
                .build();

        when(scheduleRepository.findAll()).thenReturn(List.of(schedule));
        when(userRepository.findByUserID(schedule.getUserId())).thenReturn(user);

        atEventChecker.checkEventTime();

        assertEquals(1, user.getOnTime());
        assertEquals(0, user.getLate());
    }

    @Test
    public void userIsNotWithin100m() {
        Instant now = Instant.now();

        Schedule schedule = Schedule.builder()
                .userId(1)
                .dateTime(now)
                .latitude(53.4327941)
                .longitude(6.2804578)
                .build();

        User user = User.builder()
                .userID(1)
                .lastLatitude(53.3327941)
                .lastLongitude(6.2804578)
                .onTime(0)
                .late(0)
                .build();

        when(scheduleRepository.findAll()).thenReturn(List.of(schedule));
        when(userRepository.findByUserID(schedule.getUserId())).thenReturn(user);

        atEventChecker.checkEventTime();

        assertEquals(0, user.getOnTime());
        assertEquals(1, user.getLate());
    }

}