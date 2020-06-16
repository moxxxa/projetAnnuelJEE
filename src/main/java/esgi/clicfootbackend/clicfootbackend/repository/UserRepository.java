package esgi.clicfootbackend.clicfootbackend.repository;

import esgi.clicfootbackend.clicfootbackend.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u " +
            "where u.email = ?1")
    User getUserByEmail(String email);

    @Query("select u from User u " +
            "where u.token = ?1")
    User findByToken(String token);

    @Query(value = "SELECT u FROM User u where u.email = ?1 and u.password = ?2 ")
    User login(String email, String password);

}
