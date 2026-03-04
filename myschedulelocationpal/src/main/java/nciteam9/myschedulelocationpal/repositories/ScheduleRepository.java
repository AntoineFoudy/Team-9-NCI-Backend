package nciteam9.myschedulelocationpal.repositories;

import nciteam9.myschedulelocationpal.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findByuserId(Integer userId);
}
