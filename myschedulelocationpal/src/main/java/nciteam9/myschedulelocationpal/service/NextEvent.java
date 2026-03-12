package nciteam9.myschedulelocationpal.service;


import lombok.AllArgsConstructor;
import nciteam9.myschedulelocationpal.entities.Login;
import nciteam9.myschedulelocationpal.entities.Schedule;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import nciteam9.myschedulelocationpal.repositories.ScheduleRepository;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Service
public class NextEvent {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final SendEmail sendEmail;

    /*
    Control Flow:
    Checking each user's events and checking if an event is in 20 minutes
    If a user has an event in 20 minutes the send the user an email
     */
    @Scheduled(fixedRate = 600000)
    public void controlFlow() {
        List<User> users = userRepository.findAll();
        for(User user : users) {
            List<Schedule> schedules = scheduleRepository.findByuserId(user.getUserID());
            for(Schedule schedule : schedules) {
                Instant event = schedule.getDateTime();
                if(eventInNextTimeFrame(event)) {
                    Login login = loginRepository.findByUserID(user.getUserID());
                    String email = login.getEmail();
                    // System.out.println("Email: " +email + "First Name: " +user.getFirstName() + "Event: " + schedule.toString());
                    sendEmail.sendMessage(email, user.getFirstName(), schedule.toString());
                }
            }
        }
    }

    /*
    Code based off of documentation
    @
    https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html
     */
    private boolean eventInNextTimeFrame(Instant dateTime) {
        // Gets the time now and the value determined by betweenNowAndThenValue
        int betweenNowAndThenValue = 20;
        Instant now = Instant.now();
        Instant nowPlus20 = now.plus(Duration.ofMinutes(betweenNowAndThenValue));

        // Checks if the event is within 20 minutes
        if(dateTime.isAfter(now) && dateTime.isBefore(nowPlus20)) {
            System.out.println(dateTime + " Is within 20 mins");
            return true;
        }
        else {
            System.out.println(dateTime + " Is not within 20 mins");
            return false;
        }
    }




}
