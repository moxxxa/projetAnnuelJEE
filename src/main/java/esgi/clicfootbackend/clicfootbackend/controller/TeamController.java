package esgi.clicfootbackend.clicfootbackend.controller;

import esgi.clicfootbackend.clicfootbackend.entity.Player;
import esgi.clicfootbackend.clicfootbackend.entity.SearchResult;
import esgi.clicfootbackend.clicfootbackend.entity.Team;
import esgi.clicfootbackend.clicfootbackend.service.RabbitMQService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    private RabbitMQService rabbitService;

    @GetMapping("/search/team/{name}")
    public ResponseEntity<Object> searchTeam(@PathVariable("name") String name){
        if(name.length() > 4){
            SearchResult result = rabbitService.teamSendSearchRequest(name);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/team/stats/{id}")
    public ResponseEntity<Object> teamStats(@PathVariable("id") int id){
        Team stats = rabbitService.teamSendTeamStatsRequest(id);
        return ResponseEntity.ok(stats);
    }

}
