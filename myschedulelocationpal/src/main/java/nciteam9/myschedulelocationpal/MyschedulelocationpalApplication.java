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
/*
    private final SendEmail se;
    private final NextEvent ne;

    public MyschedulelocationpalApplication(SendEmail se, NextEvent ne) {
        this.se = se;
        this.ne = ne;
    }

 */


    public static void main(String[] args) {
        SpringApplication.run(MyschedulelocationpalApplication.class, args);
    }
/*
To allow code to run on program start to test the code

    @EventListener(ApplicationReadyEvent.class)
    public void send() {
        se.sendMessage("foudyantoine@gmail.com", "test", "Hello World");
    }

    @EventListener(ApplicationReadyEvent.class)
    private void getUserId() {
        ne.controlFlow();
    }
*/
}
