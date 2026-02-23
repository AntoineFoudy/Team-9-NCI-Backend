package nciteam9.myschedulelocationpal.controllers;

import nciteam9.myschedulelocationpal.repositories.LoginRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @GetMapping("/login")
    public boolean getLogin(
            @RequestParam String loginEmail,
            @RequestParam String loginPassword
    ) {
        return loginRepository.existsByEmailAndPassword(loginEmail, loginPassword);
    }
}
