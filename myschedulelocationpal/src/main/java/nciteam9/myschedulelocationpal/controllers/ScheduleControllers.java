package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.dtos.ScheduleDto;
import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import nciteam9.myschedulelocationpal.service.GeoCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/schedule")
public class ScheduleControllers {

    private final ScheduleRepository scheduleRepository;
    private final GeoCode geoCode;

    public ScheduleControllers(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
        this.geoCode = new GeoCode();
    }

    /*
    Sets up the RESTFUL POST Method to store a schedule
     */
    @PostMapping
    public ResponseEntity<Schedule> setSchedule(@RequestBody ScheduleDto scheduleDto) throws Exception{
        ArrayList<Double> coords = geoCode.addressToCoords(scheduleDto.getAddress());

        Schedule schedule = new Schedule();
        schedule.setUserId(scheduleDto.getUserId());
        schedule.setDateTime(scheduleDto.getDateTime());
        schedule.setLatitude(coords.get(0));
        schedule.setLongitude(coords.get(1));

        Schedule save = scheduleRepository.save(schedule);
        return ResponseEntity.ok(save);
    }

    /*
    Sets up the RESTFUL GET Method to retrieve all the scheduled events for a specific user
    */
    @GetMapping
    public ResponseEntity<List<Schedule>> getSchedule(
            @RequestParam int userId
    ) {
        List<Schedule> requestSchedules = scheduleRepository.findByuserId(userId);
        return ResponseEntity.ok(requestSchedules);
    }
}
