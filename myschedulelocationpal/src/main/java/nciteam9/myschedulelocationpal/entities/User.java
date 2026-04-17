package nciteam9.myschedulelocationpal.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userID;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "LastLatitude")
    private Double lastLatitude;

    @Column(name = "LastLongitude")
    private Double lastLongitude;

    @Column(name = "OnTime", columnDefinition = "INT DEFAULT 0")
    private Integer onTime = 0;

    @Column(name = "Late", columnDefinition = "INT DEFAULT 0")
    private Integer late = 0;

}
