package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentModel;
import esgi.clicfootbackend.clicfootbackend.Model.User;
import esgi.clicfootbackend.clicfootbackend.repository.TournamentRepository;
import esgi.clicfootbackend.clicfootbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;

    public TournamentService(UserRepository userRepository, TournamentRepository tournamentRepository) {
        this.userRepository = userRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public boolean save(TournamentModel tournamentModel, String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            tournamentRepository.save(tournamentModel);
            // tournamentModel contains the liste of idTeams
            // client lourd need to get the result of the tournament of tournamentModel  and the return the result in form of TournamentResult as bellow
//            if (tournamentModel.getTournament().size() >2) {
//                return new TournamentResult("Barcelone", "Paris SG", "Bayern", "10%", "20%", "30%");
//            }
//            return new TournamentResult("Bacelone", "Paris SG", "55%", "45%");
            return true;
        }
        return false;
    }

    public List<TournamentModel> getAll(String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            return tournamentRepository.findByToken(token);
        }
        return null;
    }
}
