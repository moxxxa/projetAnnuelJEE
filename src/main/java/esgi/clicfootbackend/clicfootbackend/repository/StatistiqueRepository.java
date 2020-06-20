package esgi.clicfootbackend.clicfootbackend.repository;

import esgi.clicfootbackend.clicfootbackend.Model.Statistique.StatistiqueModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatistiqueRepository extends JpaRepository<StatistiqueModel, Long> {
}
