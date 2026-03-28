package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.dtos.LastLocationDto;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
    This Class is not currently used
    Was just to check if the backend was connecting properly to the DB
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public void setLastLocation(@RequestBody LastLocationDto lastLocationDto) throws Exception{
        User user = userRepository.findById(lastLocationDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setUserID(lastLocationDto.getUserId());
        user.setLastLatitude(lastLocationDto.getLastLatitude());
        user.setLastLongitude(lastLocationDto.getLastLongitude());

        userRepository.save(user);

    }
}