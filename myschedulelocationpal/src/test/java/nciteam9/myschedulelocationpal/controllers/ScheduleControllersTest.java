package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.dtos.ScheduleDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleControllersTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private GeoCode geoCode;

    @InjectMocks
    private ScheduleControllers scheduleControllers;

    private GeoCode nonMockedGeoCode;


    @Test
    public void createNewEvent() throws Exception {
        Instant now = Instant.now();

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setScheduleId(1);
        scheduleDto.setDateTime(now);
        scheduleDto.setAddress("21+Wolseley+Street,+Dublin,+D08E2C2");
        scheduleDto.setDescription("Create");


        Schedule schedule = new Schedule();
        schedule.setScheduleId(1);
        schedule.setDateTime(now);
        schedule.setLatitude(53.3327941);
        schedule.setLongitude(-6.2804578);
        schedule.setDescription("Create");

        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        ResponseEntity<Schedule> response = scheduleControllers.setSchedule(scheduleDto);
        Schedule result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(1, result.getScheduleId());
        assertEquals(now, result.getDateTime());
        assertEquals(schedule.getLatitude(), result.getLatitude());
        assertEquals(schedule.getLongitude(), result.getLongitude());
        assertEquals(schedule.getDescription(), result.getDescription());
    }

    @Test
    public void getUserEvents() throws Exception {
        Instant now = Instant.now();

        Schedule schedule1 = new Schedule();
        schedule1.setScheduleId(1);
        schedule1.setUserId(1);
        schedule1.setDateTime(now);
        schedule1.setLatitude(53.3327941);
        schedule1.setLongitude(-6.2804578);
        schedule1.setDescription("Get 1");

        Schedule schedule2 = new Schedule();
        schedule2.setScheduleId(2);
        schedule2.setUserId(1);
        schedule2.setDateTime(now);
        schedule2.setLatitude(53.3489292);
        schedule2.setLongitude(-6.2429928);
        schedule2.setDescription("Get 2");

        Schedule schedule3 = new Schedule();
        schedule3.setScheduleId(3);
        schedule3.setUserId(1);
        schedule3.setDateTime(now);
        schedule3.setLatitude(53.3439886);
        schedule3.setLongitude(-6.2624303);
        schedule3.setDescription("Get 3");

        ArrayList<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule1);
        schedules.add(schedule2);
        schedules.add(schedule3);

        User user = User.builder()
                .userID(1)
                .build();

        when(scheduleRepository.findByuserId(any())).thenReturn(schedules);

        ResponseEntity<List<ScheduleDto>> response = scheduleControllers.getSchedule(user.getUserID());

        List<ScheduleDto> result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        this.nonMockedGeoCode = new GeoCode();
        for(int i = 0; i < schedules.size(); i++) {
            Schedule currentSchedule = schedules.get(i);
            String expectedAddress = nonMockedGeoCode.coordsToAddress(currentSchedule.getLatitude(), currentSchedule.getLongitude());

            ScheduleDto currentResultSchedule = result.get(i);

            assertEquals(currentSchedule.getScheduleId(), currentResultSchedule.getScheduleId());
            assertEquals(currentSchedule.getUserId(), currentResultSchedule.getUserId());
            assertEquals(expectedAddress, currentResultSchedule.getAddress());
            assertEquals(currentSchedule.getDescription(), currentResultSchedule.getDescription());
        }
    }

    @Test
    public void deleteEvent() {

        Schedule schedule = Schedule.builder()
                .scheduleId(1)
                .build();

        ResponseEntity<?> response = scheduleControllers.deleteSchedule(schedule.getScheduleId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}