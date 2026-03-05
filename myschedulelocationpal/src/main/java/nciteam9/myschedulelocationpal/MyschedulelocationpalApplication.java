package nciteam9.myschedulelocationpal;

import nciteam9.myschedulelocationpal.service.SendEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MyschedulelocationpalApplication {
    private final SendEmail se;

    public MyschedulelocationpalApplication(SendEmail se) {
        this.se = se;
    }


    public static void main(String[] args) {
        SpringApplication.run(MyschedulelocationpalApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void send() {
        se.sendMessage("foudyantoine@gmail.com", "test", "Hello World");
    }

}
