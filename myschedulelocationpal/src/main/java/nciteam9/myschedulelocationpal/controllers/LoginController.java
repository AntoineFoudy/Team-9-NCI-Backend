package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.entities.Login;
import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    private Login requestLogin;

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @GetMapping("/login")
    public ResponseEntity<Login> getLogin(
            @RequestParam String loginEmail,
            @RequestParam String loginPassword
    ) {
        this.requestLogin = loginRepository.findByEmailAndPassword(loginEmail, loginPassword);

        if(requestLogin == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(requestLogin);
    }
}
