package esgi.clicfootbackend.clicfootbackend.controller;


import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsModel;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentModel;
import esgi.clicfootbackend.clicfootbackend.Model.Tournament.TournamentResult;
import esgi.clicfootbackend.clicfootbackend.configuration.UserConfig;
import esgi.clicfootbackend.clicfootbackend.mailNotifyer.MessageFactory;
import esgi.clicfootbackend.clicfootbackend.mailNotifyer.Sender;
import esgi.clicfootbackend.clicfootbackend.mailNotifyer.SubjectFactory;
import esgi.clicfootbackend.clicfootbackend.service.RabbitMQService;
import esgi.clicfootbackend.clicfootbackend.service.TournamentService;
import esgi.clicfootbackend.clicfootbackend.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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
    private UserService userService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private RabbitMQService rabbitService;

    @Autowired
    Sender sender;

    @PostMapping("/save")
    public ResponseEntity<TournamentResult> save(@RequestBody TournamentModel tournamentModel, @RequestHeader("Authorization") String token) {
        logger.info("Tournament Request in mode Get with the next authorization: " + token);
        logger.info("the request token is: " + userConfig.extractToken(token));
        logger.info("processing to get store the tournament request ...");
        String fetshedUserEmail = userService.findByToken(userConfig.extractToken(token));
        if(fetshedUserEmail != "") {
            TournamentModel result = tournamentService.save(tournamentModel);
            if(result != null) {
                try {
                    sender.sendMail(fetshedUserEmail, MessageFactory.tournamentRequestAvailable(), SubjectFactory.subject("Tournament"));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                rabbitService.tournamentRequest(result);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TournamentModel>> getAll(@RequestHeader("Authorization") String token) {
        logger.info("tournaments get all request with the next authorization: " + token);
        logger.info("the request token is: " + userConfig.extractToken(token));
        logger.info("processing to get tournaments ...");
        return new ResponseEntity<List<TournamentModel>>(tournamentService.getAll(userConfig.extractToken(token)), HttpStatus.OK);
    }
}
