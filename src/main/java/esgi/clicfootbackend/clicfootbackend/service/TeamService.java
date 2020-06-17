package esgi.clicfootbackend.clicfootbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import esgi.clicfootbackend.clicfootbackend.Model.Player;
import esgi.clicfootbackend.clicfootbackend.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    public Team getStatsFromId(int id, Team team) {
        String url = "https://api-football-v1.p.rapidapi.com/v2/players/player/" + id;
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(url, HttpMethod.GET, request, JsonNode.class, 1);
        if (result.getStatusCode() == HttpStatus.OK) {
            JsonNode root = result.getBody();
            JsonNode content = root.get("api");

            ArrayNode jsonTeam = (ArrayNode) content.get("teams");
            ObjectNode objectTeam = (ObjectNode) jsonTeam.get(0);

            team.setId(objectTeam.get("team_id").asInt());
            team.setName(objectTeam.get("name").asText());
            team.setImage(objectTeam.get("logo").asText());

            return team;
        } else {
            System.out.println("Request failed");
            return new Team();
        }
    }

}