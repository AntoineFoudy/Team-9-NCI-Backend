package nciteam9.myschedulelocationpal.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScheduleID")
    private Integer scheduleId;

    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "DateTime", nullable = false)
    private Instant dateTime;

    @Column(name = "Latitude")
    private double latitude;

    @Column(name = "Longitude")
    private double longitude;

    @Override
    public String toString() {
        return "ScheduleID: " + scheduleId + " DateTime: " + dateTime + " Latitue: " + latitude + " Longitue: " + longitude;
    }

}
