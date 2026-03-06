package nciteam9.myschedulelocationpal.repositories;

import nciteam9.myschedulelocationpal.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Login, Integer> {
    /*
    https://docs.spring.io/spring-data/jpa/reference/4.0/repositories/query-keywords-reference.html
    All code in the Class was gathered from this documentation
     */

    Login findByEmailAndPassword(String loginEmail, String loginPassword);

    Login findByUserID(int userID);
}

