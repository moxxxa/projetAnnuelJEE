package esgi.clicfootbackend.clicfootbackend.controller;

import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsModel;
import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsResult;
import esgi.clicfootbackend.clicfootbackend.Model.Statistique.StatistiqueModel;
import esgi.clicfootbackend.clicfootbackend.configuration.UserConfig;
import esgi.clicfootbackend.clicfootbackend.service.PronosticsService;
import esgi.clicfootbackend.clicfootbackend.service.StatistiqueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "statistique")
public class StatistiqueController {

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
    private StatistiqueService statistiqueService;

    @PostMapping("/save")
    public ResponseEntity<StatistiqueModel> save(@RequestBody StatistiqueModel statistiqueModel, @RequestHeader("Authorization") String token) {
        logger.info("Statistique Request in mode Post with the next authorization: " + token);
        logger.info("the request token is: " + userConfig.extractToken(token));
        logger.info("processing to store the Statistique request ...");
        return new ResponseEntity<StatistiqueModel>(statistiqueService.saveStatistique(statistiqueModel, userConfig.extractToken(token)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StatistiqueModel>> getAll(@RequestHeader("Authorization") String token) {
        logger.info("Statistique to get all statistique with the next authorization: " + token);
        logger.info("the request token is: " + userConfig.extractToken(token));
        logger.info("processing to get all statistique ...");
        return new ResponseEntity<List<StatistiqueModel>>(statistiqueService.getAll(userConfig.extractToken(token)), HttpStatus.OK);
    }

}
