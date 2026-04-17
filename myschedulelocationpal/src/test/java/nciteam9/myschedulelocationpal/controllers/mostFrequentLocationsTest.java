package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import nciteam9.myschedulelocationpal.service.GeoCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.xml.transform.Result;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class mostFrequentLocationsTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private mostFrequentLocations mostFrequentLocations;

    @Test
    public void userHasEventHistoryWithDifferentAmountsForEachEvent() throws Exception {
        Instant eventTime = Instant.now();

        List<Schedule> schedules = new ArrayList<>();

        Schedule schedule1 = Schedule.builder()
                .scheduleId(1)
                .userId(1)
                .description("College")
                .latitude(53.3489292)
                .longitude(-6.2429928)
                .dateTime(eventTime)
                .build();

        Schedule schedule2 = Schedule.builder()
                .scheduleId(2)
                .userId(1)
                .description("College")
                .latitude(53.3489292)
                .longitude(-6.2429928)
                .dateTime(eventTime)
                .build();

        Schedule schedule3 = Schedule.builder()
                .scheduleId(3)
                .userId(1)
                .description("College")
                .latitude(53.3489292)
                .longitude(-6.2429928)
                .dateTime(eventTime)
                .build();

        Schedule schedule4 = Schedule.builder()
                .scheduleId(4)
                .userId(1)
                .description("College")
                .latitude(53.3489292)
                .longitude(-6.2429928)
                .dateTime(eventTime)
                .build();

        Schedule schedule5 = Schedule.builder()
                .scheduleId(5)
                .userId(1)
                .description("College")
                .latitude(53.3489292)
                .longitude(-6.2429928)
                .dateTime(eventTime)
                .build();

        Schedule schedule6 = Schedule.builder()
                .scheduleId(6)
                .userId(1)
                .description("Home")
                .latitude(53.3327941)
                .longitude(-6.2804578)
                .dateTime(eventTime)
                .build();

        Schedule schedule7 = Schedule.builder()
                .scheduleId(7)
                .userId(1)
                .description("Home")
                .latitude(53.3327941)
                .longitude(-6.2804578)
                .dateTime(eventTime)
                .build();

        Schedule schedule8 = Schedule.builder()
                .scheduleId(8)
                .userId(1)
                .description("Pub")
                .latitude(53.3439886)
                .longitude(-6.2624303)
                .dateTime(eventTime)
                .build();

        Schedule schedule9 = Schedule.builder()
                .scheduleId(9)
                .userId(1)
                .description("Pub")
                .latitude(53.3439886)
                .longitude(-6.2624303)
                .dateTime(eventTime)
                .build();

        Schedule schedule10 = Schedule.builder()
                .scheduleId(10)
                .userId(1)
                .description("Pub")
                .latitude(53.3439886)
                .longitude(-6.2624303)
                .dateTime(eventTime)
                .build();

        Schedule schedule11 = Schedule.builder()
                .scheduleId(11)
                .userId(1)
                .description("Pub")
                .latitude(53.3439886)
                .longitude(-6.2624303)
                .dateTime(eventTime)
                .build();

        User user = User.builder()
                .userID(1)
                .build();

        schedules.add(schedule1);
        schedules.add(schedule2);
        schedules.add(schedule3);
        schedules.add(schedule4);
        schedules.add(schedule5);
        schedules.add(schedule6);
        schedules.add(schedule7);
        schedules.add(schedule8);
        schedules.add(schedule9);
        schedules.add(schedule10);
        schedules.add(schedule11);

        when(scheduleRepository.findByuserId(user.getUserID())).thenReturn(schedules);

        ResponseEntity<List<String>> response = mostFrequentLocations.mostLocation(user.getUserID());

        assertEquals("National College of Ireland, 1 Mayor Street Lower, International Financial Services Centre, Dublin 1, D01 Y300, Ireland", response.getBody().get(0));
        assertEquals("Ard Finnan House, 17 Trinity St, Dublin, D02 W281, Ireland", response.getBody().get(1));
        assertEquals("21 Wolseley St, Dublin 8, D08 E2C2, Ireland", response.getBody().get(2));
    }

    @Test
    public void userHasNoEventHistory() throws Exception {
        Instant eventTime = Instant.now();

        List<Schedule> schedules = new ArrayList<>();
        List<?> expectedResult = new ArrayList<>();


        User user = User.builder()
                .userID(1)
                .build();

        when(scheduleRepository.findByuserId(user.getUserID())).thenReturn(schedules);

        ResponseEntity<List<String>> response = mostFrequentLocations.mostLocation(user.getUserID());

        assertEquals(expectedResult, response.getBody());
    }
}