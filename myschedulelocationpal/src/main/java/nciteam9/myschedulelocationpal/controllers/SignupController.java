package nciteam9.myschedulelocationpal.controllers;


import nciteam9.myschedulelocationpal.dtos.SignupDto;
import nciteam9.myschedulelocationpal.entities.Login;
import nciteam9.myschedulelocationpal.entities.User;
import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import nciteam9.myschedulelocationpal.repositories.UserRepository;
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
    public ResponseEntity userSignup(@RequestBody SignupDto signupDto) throws Exception{
        User user = new User();
        user.setFirstName(signupDto.getFirstName());
        user.setLastName(signupDto.getLastName());
        User saveUser = userRepository.save(user);

        Login login = new Login();
        login.setUserID(user.getUserID());
        login.setEmail(signupDto.getEmail());
        login.setPassword(signupDto.getPassword());

        Login saveLogin = loginRepository.save(login);

        return ResponseEntity.ok(signupDto);
    }
}
