package nciteam9.myschedulelocationpal.service;

import lombok.AllArgsConstructor;
import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Service
public class AtEventChecker {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 60000)
    public void checkEventTime() {
        System.out.println("Running");
        List<Schedule> schedules = scheduleRepository.findAll();
        for(Schedule schedule : schedules) {
            Instant event = schedule.getDateTime();
            if(eventInNextTimeFrame(event)) {
                double eventLat = schedule.getLatitude();
                double eventLng = schedule.getLongitude();
                User user = userRepository.findById(schedule.getUserId())
                        .orElseThrow(() -> new RuntimeException("User Not Found"));
                double userLat = user.getLastLatitude();
                double userLng = user.getLastLongitude();
                if(distanceToEvent(eventLat, eventLng, userLat, userLng) <= 0.1) {
                    System.out.println("On time");
                    user.setOnTime(user.getOnTime() + 1);
                }
                else {
                    System.out.println("Late");
                    user.setOnTime(user.getLate() + 1);
                }

                User save = userRepository.save(user);
            }
        }
    }

    private boolean eventInNextTimeFrame(Instant dateTime) {
        // Gets the time now and the value determined by betweenNowAndThenValue
        int betweenNowAndThenValue = 1;
        Instant now = Instant.now();
        Instant nowPlusTime = now.plus(Duration.ofMinutes(betweenNowAndThenValue));

        // Checks if the event is within 1 minutes
        return dateTime.isAfter(now) && dateTime.isBefore(nowPlusTime);
    }



    // Code adopted from https://www.geeksforgeeks.org/dsa/haversine-formula-to-find-distance-between-two-points-on-a-sphere/
    public double distanceToEvent(double latEvent, double lngEvent, double latUser, double lngUser) {
        double earthRadius = 6371;

        // Distance between latitudes and longitudes
        double dLat = Math.toRadians(latUser - latEvent);
        double dLgn = Math.toRadians(lngUser - lngEvent);

        // Convert to radians
        latEvent = Math.toRadians(latEvent);
        latUser = Math.toRadians(latUser);

        // Haversine Formula
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLgn / 2), 2) *
                        Math.cos(latUser) * Math.cos(latEvent);
        double c = 2 * Math.asin(Math.sqrt(a));

        return earthRadius * c;
    }
}
