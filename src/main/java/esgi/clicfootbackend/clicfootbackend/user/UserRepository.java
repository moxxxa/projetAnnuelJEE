package esgi.clicfootbackend.clicfootbackend.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u " +
            "where u.username = ?1")
    Optional<User> getUserByUsername(String username);

}
