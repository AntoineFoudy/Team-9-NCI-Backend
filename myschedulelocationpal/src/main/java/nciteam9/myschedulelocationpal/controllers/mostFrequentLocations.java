package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import nciteam9.myschedulelocationpal.service.GeoCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/location")
public class mostFrequentLocations {

    private final ScheduleRepository scheduleRepository;
    private final GeoCode geoCode = new GeoCode();


    public mostFrequentLocations(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping
    public ResponseEntity<List<String>> mostLocation(@RequestParam Integer userId) throws Exception {
        // location in String, Amount of time appear in Integer
        Map<String, Integer> location = new HashMap<>();

        List<Schedule> schedules = scheduleRepository.findByuserId(userId);

        for(Schedule s : schedules) {
            String address = geoCode.coordsToAddress(s.getLatitude(), s.getLongitude());
            location.put(address, location.getOrDefault(address, 0) + 1);
        }

        List<String> fiveMost = location.entrySet().stream()
                .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();

        return ResponseEntity.ok(fiveMost);

    }
}
