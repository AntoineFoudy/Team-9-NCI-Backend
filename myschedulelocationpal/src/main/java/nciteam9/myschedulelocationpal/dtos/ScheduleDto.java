package nciteam9.myschedulelocationpal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class ScheduleDto {
    private int userId;
    private Instant dateTime;
    private String address;
}
