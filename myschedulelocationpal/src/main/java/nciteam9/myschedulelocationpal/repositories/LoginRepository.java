package nciteam9.myschedulelocationpal.repositories;

import nciteam9.myschedulelocationpal.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Login, Integer> {
    /*
    https://docs.spring.io/spring-data/jpa/reference/4.0/repositories/query-keywords-reference.html
    Code to filter though values and return boolean value, based off of here
     */

    // boolean existsByEmailAndPassword(String loginEmail, String loginPassword);

    Login findByEmailAndPassword(String loginEmail, String loginPassword);
}

