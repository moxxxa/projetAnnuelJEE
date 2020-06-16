package esgi.clicfootbackend.clicfootbackend.controller;

import esgi.clicfootbackend.clicfootbackend.Model.Player;
import esgi.clicfootbackend.clicfootbackend.Model.SearchResult;
import esgi.clicfootbackend.clicfootbackend.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    private RabbitMQService rabbitService;

    @GetMapping("/search/player/{name}")
    public ResponseEntity<Object> searchPlayer(@PathVariable("name") String name){
        if(name.length() > 4){
            SearchResult result = rabbitService.playerSendSearchRequest(name);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/player/stats/{id}")
    public ResponseEntity<Object> playerStats(@PathVariable("id") int id){
        Player player = rabbitService.playerSendPlayerStatsRequest(id);
        return ResponseEntity.ok(player);
    }
}
