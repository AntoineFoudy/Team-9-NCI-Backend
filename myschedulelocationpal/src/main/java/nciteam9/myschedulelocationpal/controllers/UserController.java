package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/*
    This Class is not currently used
    Was just to check if the backend was connecting properly to the DB
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}