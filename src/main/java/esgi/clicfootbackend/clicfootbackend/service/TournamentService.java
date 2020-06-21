package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamenetModel;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentResult;
import esgi.clicfootbackend.clicfootbackend.Model.User;
import esgi.clicfootbackend.clicfootbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {

    private final UserRepository userRepository;

    public TournamentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TournamentResult predict(TournamenetModel tournamenetModel, String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            // tournamentModel contains the liste of idTeams
            // client lourd need to get the result of the tournament of tournamentModel  and the return the result in form of TournamentResult as bellow
            if (tournamenetModel.getTournament().size() >2) {
                return new TournamentResult("Barcelone", "Paris SG", "Bayern", "10%", "20%", "30%");
            }
            return new TournamentResult("Bacelone", "Paris SG", "55%", "45%");
        }
        return null;
    }
}
