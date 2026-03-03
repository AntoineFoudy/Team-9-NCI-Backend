package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ScheduleControllers {

    private final ScheduleRepository scheduleRepository;

    public ScheduleControllers(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // Sets up the RESTFUL POST Method
    @PostMapping("/schedule")
    public ResponseEntity<Schedule> getSchedule(@RequestBody Schedule schedule) {
        Schedule save = scheduleRepository.save(schedule);
        return ResponseEntity.ok(save);
    }
}
