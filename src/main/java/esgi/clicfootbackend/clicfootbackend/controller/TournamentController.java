package esgi.clicfootbackend.clicfootbackend.controller;


import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsModel;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentModel;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentResult;
import esgi.clicfootbackend.clicfootbackend.configuration.UserConfig;
import esgi.clicfootbackend.clicfootbackend.service.TournamentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/save")
    public ResponseEntity<TournamentResult> save(@RequestBody TournamentModel tournamentModel, @RequestHeader("Authorization") String token) {
        logger.info("Tournament Request in mode Get with the next authorization: " + token);
        logger.info("the request token is: " + userConfig.extractToken(token));
        logger.info("processing to get store the tournament request ...");
        return new ResponseEntity((tournamentService.save(tournamentModel, userConfig.extractToken(token)) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TournamentModel>> getAll(@RequestHeader("Authorization") String token) {
        logger.info("tournaments get all request with the next authorization: " + token);
        logger.info("the request token is: " + userConfig.extractToken(token));
        logger.info("processing to get tournaments ...");
        return new ResponseEntity<List<TournamentModel>>(tournamentService.getAll(userConfig.extractToken(token)), HttpStatus.OK);
    }
}
