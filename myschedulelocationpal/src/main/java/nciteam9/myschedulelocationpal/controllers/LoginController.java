package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.dtos.LoginDto;
import nciteam9.myschedulelocationpal.dtos.RequestLoginDto;
import nciteam9.myschedulelocationpal.entities.Login;
import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // Uses values passed by frontend to find a user's info and or pass the correct message
    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody RequestLoginDto request) {

        // Use LoginDto to protect users info and only pass relevant info to the frontend
        LoginDto loginDto = new LoginDto();

        // Check if email or password is incorrect
        Login requestLogin = loginRepository.findByEmail(request.getLoginEmail());
        if(requestLogin != null && requestLogin.getPassword().equals(request.getLoginPassword())) {
            loginDto.setUserID(requestLogin.getUserID());
            loginDto.setFirstName(requestLogin.getUser().getFirstName());
            loginDto.setLastName(requestLogin.getUser().getLastName());
        }
        // If email or password is incorrect return LoginDto with empty values
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginDto);
        }

        // If user is found sends back the userID and name
        return ResponseEntity.ok(loginDto);
    }
}
