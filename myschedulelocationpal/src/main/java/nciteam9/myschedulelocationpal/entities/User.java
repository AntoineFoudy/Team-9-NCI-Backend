package nciteam9.myschedulelocationpal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
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

    @Column(name = "OnTime")
    private Integer onTime;

    @Column(name = "Late")
    private Integer late;

}
