package esgi.clicfootbackend.clicfootbackend.repository;

import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PronosticsRepository extends JpaRepository<PronosticsModel, Long> {

}
