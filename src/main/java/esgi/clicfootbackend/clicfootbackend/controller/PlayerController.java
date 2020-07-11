package esgi.clicfootbackend.clicfootbackend.controller;

import esgi.clicfootbackend.clicfootbackend.Model.API.SearchResults;
import esgi.clicfootbackend.clicfootbackend.Model.Player.Player;
import esgi.clicfootbackend.clicfootbackend.service.PlayerService;
import esgi.clicfootbackend.clicfootbackend.service.RabbitMQService;
import esgi.clicfootbackend.clicfootbackend.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    private RabbitMQService rabbitService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/search/player/{name}")
    public ResponseEntity<SearchResults> searchPlayer(@PathVariable("name") String name){
        if(name.length() >= 4){
            SearchResults result = searchService.searchPlayer(name);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/player/stats/{id}")
    public ResponseEntity<Object> playerStats(@PathVariable("id") int id) {
        Player player = playerService.getStatsFromId(id);
        return ResponseEntity.ok(player);
    }
    // RabbitMQ communication
    /*@GetMapping("/search/player/{name}")
    public ResponseEntity<Object> searchPlayer(@PathVariable("name") String name){
        if(name.length() > 4){
            SearchResult result = rabbitService.playerSendSearchRequest(name);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }*/

    // RabbitMQ communication
    /*
    @GetMapping("/player/stats/{id}")
    public ResponseEntity<Object> playerStats(@PathVariable("id") int id){
        Player player = rabbitService.playerSendPlayerStatsRequest(id);
        return ResponseEntity.ok(player);
    }*/
}
