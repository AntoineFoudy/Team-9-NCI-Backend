package nciteam9.myschedulelocationpal.repositories;

import nciteam9.myschedulelocationpal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}