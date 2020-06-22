package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsModel;
import esgi.clicfootbackend.clicfootbackend.Model.User;
import esgi.clicfootbackend.clicfootbackend.repository.PronosticsRepository;
import esgi.clicfootbackend.clicfootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PronosticsService {
    private final PronosticsRepository pronosticsRepository;
    private final UserRepository userRepository;

    @Autowired
    public PronosticsService(PronosticsRepository pronosticsRepository, UserRepository userRepository){
        this.pronosticsRepository = pronosticsRepository;
        this.userRepository = userRepository;
    }

    public boolean save(PronosticsModel model, String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            //Gautier, you need to send the model to the clientLourd then stock the result in the model
            // you need to create another enPoint to let me recuperate a liste of PronosticsResult
            pronosticsRepository.save(model);
            return true;
        }

        //last you need to change the constructur parameters with the client lourd result (the result is the same of the model set method above
        return false;
    }

    public List<PronosticsModel> getAll(String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            return pronosticsRepository.findByToken(token);
        }
        return null;
    }
}
