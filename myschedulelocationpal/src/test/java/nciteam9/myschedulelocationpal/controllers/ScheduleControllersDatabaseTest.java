package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.dtos.ScheduleDto;
import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import nciteam9.myschedulelocationpal.service.GeoCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource("classpath:application-test.properties")
class ScheduleControllersDatabaseTest {

    @Autowired
    private ScheduleControllers scheduleControllers;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @MockitoBean
    private GeoCode geoCode;

    @Test
    public void saveSchedule() throws Exception {

        Instant now = Instant.now();

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setUserId(1);
        scheduleDto.setAddress("21+Wolseley+Street,+Dublin,+D08E2C2");
        scheduleDto.setDateTime(now);
        scheduleDto.setDescription("Create and Save");

        when(geoCode.addressToCoords(scheduleDto.getAddress())).thenReturn(new ArrayList<>(List.of(53.3327941, -6.2804578)));

        ResponseEntity<Schedule> response = scheduleControllers.setSchedule(scheduleDto);

        Schedule result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(result.getUserId());
        assertEquals(scheduleDto.getUserId(), result.getUserId());
        assertEquals(now, result.getDateTime());
        assertEquals(53.3327941, result.getLatitude());
        assertEquals(-6.2804578, result.getLongitude());
        assertEquals(scheduleDto.getDescription(), result.getDescription());

        Optional<Schedule> db = scheduleRepository.findById(result.getScheduleId());

        assertEquals(scheduleDto.getUserId(), db.get().getUserId());
        assertEquals(now.truncatedTo(ChronoUnit.MILLIS), db.get().getDateTime().truncatedTo(ChronoUnit.MILLIS));
        assertEquals(53.3327941, db.get().getLatitude());
        assertEquals(-6.2804578, db.get().getLongitude());
        assertEquals(scheduleDto.getDescription(), db.get().getDescription());

    }

}