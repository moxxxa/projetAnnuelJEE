package esgi.clicfootbackend.clicfootbackend.controller;

import esgi.clicfootbackend.clicfootbackend.Model.API.SearchResults;
import esgi.clicfootbackend.clicfootbackend.service.RabbitMQService;
import esgi.clicfootbackend.clicfootbackend.service.SearchService;
import esgi.clicfootbackend.clicfootbackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    private RabbitMQService rabbitService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private TeamService teamService;

    // Communication RabbitMQ
    /*@GetMapping("/search/team/{name}")
    public ResponseEntity<Object> searchTeam(@PathVariable("name") String name){
        if(name.length() > 4){
            SearchResult result = rabbitService.teamSendSearchRequest(name);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }*/

    @GetMapping("/search/team/{name}")
    public ResponseEntity<SearchResults> searchTeam(@PathVariable("name") String name){
        if(name.length() >= 4){
            SearchResults result = searchService.searchTeam(name);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    // Communication RabbitMQ
    /*@GetMapping("/team/stats/{id}")
    public ResponseEntity<Object> teamStats(@PathVariable("id") int id){
        Team stats = rabbitService.teamSendTeamStatsRequest(id);
        return ResponseEntity.ok(stats);
    }*/

}
