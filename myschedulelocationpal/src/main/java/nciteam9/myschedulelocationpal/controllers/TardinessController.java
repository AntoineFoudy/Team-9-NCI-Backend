package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tardiness")
public class TardinessController {

    private final UserRepository userRepository;

    public TardinessController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getTardiness(@RequestParam Integer userId) {

        User user = userRepository.findByUserID(userId);

        ArrayList<String> userRecord = new ArrayList<>();
        String onTime = String.valueOf(user.getOnTime());
        String late = String.valueOf(user.getLate());
        userRecord.add(onTime);
        userRecord.add(late);

        return ResponseEntity.ok(userRecord);
    }
}
