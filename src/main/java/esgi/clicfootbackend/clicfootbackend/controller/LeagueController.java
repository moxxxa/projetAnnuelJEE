package esgi.clicfootbackend.clicfootbackend.controller;

import esgi.clicfootbackend.clicfootbackend.Model.League.League;
import esgi.clicfootbackend.clicfootbackend.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/leagues")
    public ResponseEntity<ArrayList<League>> getLeagues(){
        ArrayList<League> result = leagueService.getCurrentLeagues();
        return ResponseEntity.ok(result);
    }

}
