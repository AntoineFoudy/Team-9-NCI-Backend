package nciteam9.myschedulelocationpal.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLoginDto {
    private String loginEmail;
    private String loginPassword;
}
