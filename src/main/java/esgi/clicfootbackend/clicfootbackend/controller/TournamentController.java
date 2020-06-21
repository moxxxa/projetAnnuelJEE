package esgi.clicfootbackend.clicfootbackend.controller;


import esgi.clicfootbackend.clicfootbackend.Model.Statistique.StatistiqueModel;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamenetModel;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentResult;
import esgi.clicfootbackend.clicfootbackend.configuration.UserConfig;
import esgi.clicfootbackend.clicfootbackend.service.StatistiqueService;
import esgi.clicfootbackend.clicfootbackend.service.TournamentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "tournament")
public class TournamentController {
    private static Logger logger;
    static {
        try {
            // you need to do something like below instaed of Logger.getLogger(....);
            logger = LogManager.getLogger(UserController.class);
        } catch (Throwable th) {
        }
    }
    @Autowired
    private UserConfig userConfig;

    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/prediction")
    public ResponseEntity<TournamentResult> predict(@RequestBody TournamenetModel tournamenetModel, @RequestHeader("Authorization") String token) {
        logger.info("Tournament Request in mode Get with the next authorization: " + token);
        logger.info("the request token is: " + userConfig.extractToken(token));
        logger.info("processing to get the result of the tournament request ...");
        return new ResponseEntity<TournamentResult>(tournamentService.predict(tournamenetModel, userConfig.extractToken(token)), HttpStatus.OK);
    }
}
