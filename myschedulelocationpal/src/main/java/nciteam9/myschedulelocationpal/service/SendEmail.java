package nciteam9.myschedulelocationpal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("schedulelocationapp@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);

    }
}
