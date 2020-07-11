package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentModel;
import esgi.clicfootbackend.clicfootbackend.repositoryDao.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public TournamentModel save(TournamentModel tournamentModel) {
        return tournamentRepository.save(tournamentModel);
    }

    public List<TournamentModel> getAll(String token) {
        return tournamentRepository.findByToken(token);
    }

    public Optional<TournamentModel> getById(String id) {
        return tournamentRepository.findById(Long.parseLong(id));
    }
}
