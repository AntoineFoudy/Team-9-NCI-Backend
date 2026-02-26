package nciteam9.myschedulelocationpal.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Login")
public class Login {

    @Id
    @Column(name = "LoginID")
    private int loginID;

    @Column(name = "UserID")
    private int userID;

    @OneToOne
    @JoinColumn(name = "UserID", insertable = false, updatable = false)
    private User user;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;
}
