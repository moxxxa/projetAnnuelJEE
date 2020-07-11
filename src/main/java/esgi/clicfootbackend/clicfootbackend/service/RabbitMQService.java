package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsModel;
import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsRequest;
import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsResult;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentModel;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentRequest;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentResult;
import esgi.clicfootbackend.clicfootbackend.enums.ResultStatus;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitMQService {

    @Autowired
    private Queue pronosticRequestQueue;

    @Autowired
    private Queue tournamentRequestQueue;

    private PronosticsService pronosticsService;

    private TournamentService tournamentService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageConverter jsonMessageConverter;

    public RabbitMQService(final RabbitTemplate rabbitTemplate, PronosticsService pronosticsService, TournamentService tournamentService){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(jsonMessageConverter);
        this.pronosticsService = pronosticsService;
        this.tournamentService = tournamentService;
    }

    @RabbitListener(queues = "predict_match_response")
    public void pronosticResult(PronosticsResult result) {
        System.out.println(result.getId());
        Optional<PronosticsModel> current = pronosticsService.getById(result.getId());
        if(current.isPresent()){
            current.get().setAwayResult(result.getResult().getAway());
            current.get().setHomeResult(result.getResult().getHome());
            current.get().setDrawResult(result.getResult().getDraw());
            current.get().setStatus(ResultStatus.Finished);
            current.get().setHomeGoal(result.getHomeScore());
            current.get().setAwayGoal(result.getAwayScore());
            pronosticsService.save(current.get());
        }
    }


    @RabbitListener(queues = "predict_tournament_response")
    public void tournamentResult(TournamentResult result){
        Optional<TournamentModel> current = tournamentService.getById(result.getId());
        if(current.isPresent()){
            current.get().setFirstPlace(result.getFirstPlace());
            current.get().setFirstPlacePrediction((result.getFirstPlacePrediction()));

            current.get().setSecondPlace(result.getSecondPlace());
            current.get().setSecondPlacePrediction(result.getSecondPlacePrediction());

            current.get().setThirdPlace(result.getThirdPlace());
            current.get().setThirdPlacePrediction(result.getThirdPlacePrediction());

            tournamentService.save(current.get());
        }
    }

    public void pronosticRequest(PronosticsModel model){
        rabbitTemplate.setMessageConverter(jsonMessageConverter);

        PronosticsRequest request = new PronosticsRequest();
        request.setId(model.getId().toString());
        request.setAwayTeamId(model.getAwayTeamId());
        request.setHomeTeamId(model.getHomeTeamId());

        rabbitTemplate.convertAndSend(pronosticRequestQueue.getName(), request);
    }

    public void tournamentRequest(TournamentModel model){
        rabbitTemplate.setMessageConverter(jsonMessageConverter);

        TournamentRequest request = new TournamentRequest();
        request.setId(model.getId().toString());

        String[] teamsArray = new String[model.getTournament().size()];
        for(int i = 0; i < model.getTournament().size(); i++){
            teamsArray[i] = model.getTournament().get(i).toString();
        }
        request.setTeamsId(teamsArray);

        rabbitTemplate.convertAndSend(tournamentRequestQueue.getName(), request);
    }

}
