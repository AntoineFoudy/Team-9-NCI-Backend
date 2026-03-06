package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = "*")
public class ScheduleControllers {

    private final ScheduleRepository scheduleRepository;

    public ScheduleControllers(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /*
    Sets up the RESTFUL POST Method to store a schedule
     */
    @PostMapping
    public ResponseEntity<Schedule> setSchedule(@RequestBody Schedule schedule) {
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
