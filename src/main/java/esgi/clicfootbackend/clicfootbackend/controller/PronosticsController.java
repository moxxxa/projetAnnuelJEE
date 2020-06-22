package esgi.clicfootbackend.clicfootbackend.controller;

import esgi.clicfootbackend.clicfootbackend.Model.Pronostics.PronosticsModel;
import esgi.clicfootbackend.clicfootbackend.configuration.UserConfig;
import esgi.clicfootbackend.clicfootbackend.service.PronosticsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "pronostics")
public class PronosticsController {

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
    private PronosticsService pronosticsService;

    @PostMapping("/predict")
    public ResponseEntity save(@RequestBody PronosticsModel pronosticsModel, @RequestHeader("Authorization") String token) {
        logger.info("Pronostics Request in mode Post with the next authorization: " + token);
        logger.info("the request token is: " + userConfig.extractToken(token));
        logger.info("processing to store the pronostics request ...");
        return new ResponseEntity((pronosticsService.save(pronosticsModel, userConfig.extractToken(token)) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PronosticsModel>> getAll(@RequestHeader("Authorization") String token) {
        logger.info("Pronostics get all request with the next authorization: " + token);
        logger.info("the request token is: " + userConfig.extractToken(token));
        logger.info("processing to get pronostics ...");
        return new ResponseEntity<List<PronosticsModel>>(pronosticsService.getAll(userConfig.extractToken(token)), HttpStatus.OK);
    }

}