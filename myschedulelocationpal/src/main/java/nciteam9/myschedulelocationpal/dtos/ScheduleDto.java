package nciteam9.myschedulelocationpal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDto {
    private int userId;
    private Instant dateTime;
    private String address;
    private String description;
}
