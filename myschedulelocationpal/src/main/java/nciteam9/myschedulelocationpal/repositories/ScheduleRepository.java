package nciteam9.myschedulelocationpal.repositories;

import nciteam9.myschedulelocationpal.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
