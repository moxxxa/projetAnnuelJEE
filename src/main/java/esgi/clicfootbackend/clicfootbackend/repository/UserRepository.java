package esgi.clicfootbackend.clicfootbackend.repository;

import esgi.clicfootbackend.clicfootbackend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u " +
            "where u.email = ?1")
    User getUserByEmail(String email);

}
