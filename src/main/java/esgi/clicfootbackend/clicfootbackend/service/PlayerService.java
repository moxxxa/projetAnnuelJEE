package esgi.clicfootbackend.clicfootbackend.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import esgi.clicfootbackend.clicfootbackend.Model.API.PlayerInfo;
import esgi.clicfootbackend.clicfootbackend.Model.Player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    public Player getStatsFromId(int id){
        String url = "https://api-football-v1.p.rapidapi.com/v2/players/player/" + id;
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(url, HttpMethod.GET, request, JsonNode.class, 1);
        if(result.getStatusCode() == HttpStatus.OK) {
            JsonNode root = result.getBody();
            JsonNode content = root.get("api");
            Player player = new Player();

            ArrayNode jsonPlayer = (ArrayNode) content.get("players");
            ObjectNode objectPlayer = (ObjectNode) jsonPlayer.get(0);


            PlayerInfo info = new PlayerInfo();
            info.setId(objectPlayer.get("player_id").asInt());
            info.setName(objectPlayer.get("player_name").asText());
            info.setNationality(objectPlayer.get("nationality").asText());

            player.setInfo(info);
            player.setNumber(objectPlayer.get("number").asInt());
            player.setAge(objectPlayer.get("age").asInt());
            player.setHeight(objectPlayer.get("height").asText());
            player.setWeight(objectPlayer.get("weight").asText());
            player.setTeam(objectPlayer.get("team_name").asText());
            player.setGoalCount(objectPlayer.get("goals").get("total").asInt());
            player.setAssistCount(objectPlayer.get("goals").get("assists").asInt());
            player.setYellowCardCount(objectPlayer.get("cards").get("yellow").asInt());
            player.setRedCardCount(objectPlayer.get("cards").get("red").asInt() + objectPlayer.get("cards").get("yellowred").asInt());

            return player;
        }
        else{
            System.out.println("Request failed");
            return new Player();
        }

    }

}
