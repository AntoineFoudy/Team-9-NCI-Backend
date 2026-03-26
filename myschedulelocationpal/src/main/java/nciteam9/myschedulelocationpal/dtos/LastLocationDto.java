package nciteam9.myschedulelocationpal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LastLocationDto {
    private int userId;
    private Double lastLatitude;
    private Double lastLongitude;
}
