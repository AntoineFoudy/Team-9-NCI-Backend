package nciteam9.myschedulelocationpal.controllers;


import nciteam9.myschedulelocationpal.dtos.LoginDto;
import nciteam9.myschedulelocationpal.dtos.SignupDto;
import nciteam9.myschedulelocationpal.entities.Login;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/signup")
public class SignupController {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    public SignupController(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @PostMapping
    public ResponseEntity<?> userSignup(@RequestBody SignupDto signupDto) {
        // LoginDto to be sent if user is created
        LoginDto loginDto = new LoginDto();

        // Find if email already exists
        Login requestSignup = loginRepository.findByEmail(signupDto.getEmail());
        if(requestSignup == null) {
            // Create user first to generate new userID
            User user = new User();
            user.setFirstName(signupDto.getFirstName());
            user.setLastName(signupDto.getLastName());
            userRepository.save(user);

            // Using the new userID assign the login info
            Login login = new Login();
            login.setUserID(user.getUserID());
            login.setEmail(signupDto.getEmail());
            login.setPassword(signupDto.getPassword());
            loginRepository.save(login);

            // Insert the correct info for the frontend to log in the user
            loginDto.setUserID(user.getUserID());
            loginDto.setFirstName(user.getFirstName());
            loginDto.setLastName(user.getLastName());
        }
        // Code 409 if email is already in use
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(loginDto);
    }
}
