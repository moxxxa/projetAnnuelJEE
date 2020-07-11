package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsModel;
import esgi.clicfootbackend.clicfootbackend.repositoryDao.PronosticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PronosticsService {
    private final PronosticsRepository pronosticsRepository;

    @Autowired
    public PronosticsService(PronosticsRepository pronosticsRepository){
        this.pronosticsRepository = pronosticsRepository;
    }

    public PronosticsModel save(PronosticsModel model) {
        return pronosticsRepository.save(model);
    }

    public List<PronosticsModel> getAll(String token) {
        return pronosticsRepository.findByToken(token);
    }

    public Optional<PronosticsModel> getById(String id) {
        Optional<PronosticsModel> result = Optional.empty();
        if(!id.isEmpty()){
            result = pronosticsRepository.findById(Long.parseLong(id));
        }
        return result;
    }
}
