package esgi.clicfootbackend.clicfootbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import esgi.clicfootbackend.clicfootbackend.Model.API.SearchResult;
import esgi.clicfootbackend.clicfootbackend.Model.Player;
import esgi.clicfootbackend.clicfootbackend.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class TeamService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    public Team getInfoFromId(int id) {
        Team team = new Team();
        String url = "https://api-football-v1.p.rapidapi.com/v2/teams/team/" + id;
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

    public Team getSquadFromId(Team team, int season){
        String url = "https://api-football-v1.p.rapidapi.com/v2/players/squad/" + team.getId() + "/" + season;
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(url, HttpMethod.GET, request, JsonNode.class, 1);
        ArrayList<Player> players = new ArrayList<Player>();
        if(result.getStatusCode() == HttpStatus.OK){
            JsonNode root = result.getBody();
            JsonNode content = root.get("api");

            ArrayNode squad = (ArrayNode) content.get("players");
            Iterator<JsonNode> playerIterator = squad.elements();
            while(playerIterator.hasNext()){
                JsonNode currentPlayer = playerIterator.next();
                Player player = new Player();
                player.setId(currentPlayer.get("player_id").asInt());
                player.setName(currentPlayer.get("player_name").asText());
                player.setNationality(currentPlayer.get("nationality").asText());

                players.add(player);
            }
        }
        team.setPlayers(players);
        return team;
    }

    public ArrayList<Team> getTeamsByLeague(int id){
        String url = "https://api-football-v1.p.rapidapi.com/v2/teams/league/" + id;
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(url, HttpMethod.GET, request, JsonNode.class, 1);
        ArrayList<Team> teams = new ArrayList<Team>();
        if(result.getStatusCode() == HttpStatus.OK){
            JsonNode root = result.getBody();
            JsonNode content = root.get("api");

            ArrayNode jsonTeams = (ArrayNode) content.get("teams");

            Iterator<JsonNode> teamsIterator = jsonTeams.elements();
            while(teamsIterator.hasNext()){
                JsonNode currentTeam = teamsIterator.next();
                Team currentResult = new Team();

                currentResult.setId(currentTeam.get("team_id").asInt());
                currentResult.setImage(currentTeam.get("logo").asText());
                currentResult.setName(currentTeam.get("name").asText());

                teams.add(currentResult);
            }
        }

        return teams;
    }

    public Team getStatsFromId(Team team, int leagueId) {
        String url = "https://api-football-v1.p.rapidapi.com/v2/statistics/" + leagueId + "/" + team.getId();
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(url, HttpMethod.GET, request, JsonNode.class, 1);
        if (result.getStatusCode() == HttpStatus.OK) {
            JsonNode root = result.getBody();
            JsonNode content = root.get("api");

            ObjectNode jsonStats = (ObjectNode) content.get("statistics");
            ObjectNode goalsStats = (ObjectNode) jsonStats.get("goals");
            ObjectNode goalsFor = (ObjectNode) goalsStats.get("goalsFor");

            team.setGoalCount(goalsFor.get("total").asInt());
        }
        return team;
    }
}
