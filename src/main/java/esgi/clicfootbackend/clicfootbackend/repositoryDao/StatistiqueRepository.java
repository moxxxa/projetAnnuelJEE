package esgi.clicfootbackend.clicfootbackend.repositoryDao;

import esgi.clicfootbackend.clicfootbackend.Model.Statistique.StatistiqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatistiqueRepository extends JpaRepository<StatistiqueModel, Long> {

    @Query("select p from StatistiqueModel p " +
            "where p.token = ?1")
    List<StatistiqueModel> findByToken(String token);
}
