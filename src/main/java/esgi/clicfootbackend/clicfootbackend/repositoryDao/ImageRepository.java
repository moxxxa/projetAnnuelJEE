package esgi.clicfootbackend.clicfootbackend.repositoryDao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import esgi.clicfootbackend.clicfootbackend.Model.Image.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);

    void deleteByName(String name);
}

