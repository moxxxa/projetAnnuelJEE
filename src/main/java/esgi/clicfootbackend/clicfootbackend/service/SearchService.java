package esgi.clicfootbackend.clicfootbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import esgi.clicfootbackend.clicfootbackend.Model.API.SearchResult;
import esgi.clicfootbackend.clicfootbackend.Model.API.SearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class SearchService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    public SearchService(){

    }


    public SearchResults searchPlayer(String name){
        String url = "https://api-football-v1.p.rapidapi.com/v2/players/search/" + name;
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(url, HttpMethod.GET, request, JsonNode.class, 1);
        if(result.getStatusCode() == HttpStatus.OK){
            JsonNode root = result.getBody();
            System.out.println(root.asText());
            JsonNode content = root.get("api");
            SearchResults search = new SearchResults();

            search.setCount(content.get("results").asInt());

            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            ArrayNode players = (ArrayNode)content.get("players");
            Iterator<JsonNode> playersIterator = players.elements();

            while(playersIterator.hasNext()){
                JsonNode currentPlayer = playersIterator.next();
                System.out.println(currentPlayer.get("player_name").asText());
                SearchResult currentResult = new SearchResult(currentPlayer.get("player_id").asInt(),currentPlayer.get("player_name").asText());
                results.add(currentResult);
            }

            search.setResults(results);

            System.out.println(search.getResults());
            System.out.println(search.getCount());

            return search;
        }
        else{
            System.out.println("Request failed");
            return new SearchResults();
        }
    }

    public SearchResults searchTeam(String name){
        String url = "https://api-football-v1.p.rapidapi.com/v2/teams/search/" + name;
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(url, HttpMethod.GET, request, JsonNode.class, 1);

        if(result.getStatusCode() == HttpStatus.OK){
            JsonNode root = result.getBody();
            JsonNode content = root.get("api");
            SearchResults search = new SearchResults();

            search.setCount(content.get("results").asInt());

            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            ArrayNode teams = (ArrayNode)content.get("teams");
            Iterator<JsonNode> teamsIterator = teams.elements();

            while(teamsIterator.hasNext()){
                JsonNode currentTeam = teamsIterator.next();
                SearchResult currentResult = new SearchResult(currentTeam.get("team_id").asInt(), currentTeam.get("name").asText());
                results.add(currentResult);
            }

            search.setResults(results);

            return search;
        }
        else{
            System.out.println("Request failed");
            return new SearchResults();
        }
    }

}
