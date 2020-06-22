package esgi.clicfootbackend.clicfootbackend.repository;

import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PronosticsRepository extends JpaRepository<PronosticsModel, Long> {

    @Query("select p from PronosticsModel p " +
            "where p.token = ?1")
    List<PronosticsModel> findByToken(String token);
}
