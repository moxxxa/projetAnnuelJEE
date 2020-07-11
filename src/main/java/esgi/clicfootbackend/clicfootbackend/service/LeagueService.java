package esgi.clicfootbackend.clicfootbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import esgi.clicfootbackend.clicfootbackend.Model.League.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class LeagueService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    public ArrayList<League> getCurrentLeagues(){
        String url = "https://api-football-v1.p.rapidapi.com/v2/leagues/current/";
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(url, HttpMethod.GET, request, JsonNode.class, 1);

        if(result.getStatusCode() == HttpStatus.OK) {
            ArrayList<League> output = new ArrayList<League>();

            JsonNode root = result.getBody();
            JsonNode content = root.get("api");

            ArrayNode leagues = (ArrayNode)content.get("leagues");
            Iterator<JsonNode> teamsIterator = leagues.elements();

            while(teamsIterator.hasNext()){
                JsonNode currentLeague = teamsIterator.next();
                League currentResult = new League();
                currentResult.setId(currentLeague.get("league_id").asInt());
                currentResult.setName(currentLeague.get("name").asText());
                currentResult.setCountry(currentLeague.get("country").asText());
                currentResult.setImage(currentLeague.get("logo").asText());
                currentResult.setType(currentLeague.get("type").asText());
                currentResult.setYear(currentLeague.get("season").asInt());

                output.add(currentResult);
            }

            return output;
        }
        else{
            System.out.println("Request failed");
            return new ArrayList<League>();
        }
    }
}
