package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.entities.Login;
import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // Sets up the RESTFUL GET Method
    @GetMapping("/login")
    public ResponseEntity<Login> getLogin(
            @RequestParam String loginEmail,
            @RequestParam String loginPassword
    ) {
        // Reference var and excecute findBy method
        Login requestLogin = loginRepository.findByEmailAndPassword(loginEmail, loginPassword);
        // If no user is found response will be 404
        if(requestLogin == null) {
            return ResponseEntity.notFound().build();
        }
        // If user is found sends back the name and email, includes other data that can be ignored
        return ResponseEntity.ok(requestLogin);
    }
}
