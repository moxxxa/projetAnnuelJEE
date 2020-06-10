package esgi.clicfootbackend.clicfootbackend.Image;

import java.util.Optional;

import esgi.clicfootbackend.clicfootbackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);

    void deleteByName(String name);
}

