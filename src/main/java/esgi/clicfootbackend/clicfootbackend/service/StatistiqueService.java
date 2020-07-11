package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Model.Statistique.StatistiqueModel;
import esgi.clicfootbackend.clicfootbackend.Model.User.User;
import esgi.clicfootbackend.clicfootbackend.repositoryDao.StatistiqueRepository;
import esgi.clicfootbackend.clicfootbackend.repositoryDao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatistiqueService {
    private final StatistiqueRepository statistiqueRepository;
    private final UserRepository userRepository;

    @Autowired
    public StatistiqueService(StatistiqueRepository statistiqueRepository, UserRepository userRepository){
        this.statistiqueRepository = statistiqueRepository;
        this.userRepository = userRepository;
    }

    public StatistiqueModel saveStatistique(StatistiqueModel model, String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            statistiqueRepository.save(model);
        }
        return model;
    }

    public List<StatistiqueModel> getAll(String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            return statistiqueRepository.findByToken(token);
        }
        return null;
    }
}
