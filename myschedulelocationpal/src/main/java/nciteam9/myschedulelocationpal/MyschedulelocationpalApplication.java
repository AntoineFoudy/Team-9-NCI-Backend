package nciteam9.myschedulelocationpal;

import nciteam9.myschedulelocationpal.service.NextEvent;
import nciteam9.myschedulelocationpal.service.SendEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyschedulelocationpalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyschedulelocationpalApplication.class, args);
    }
}