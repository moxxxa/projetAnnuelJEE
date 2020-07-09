package esgi.clicfootbackend.clicfootbackend.Model.Pronostics;

import com.fasterxml.jackson.annotation.JsonProperty;
import esgi.clicfootbackend.clicfootbackend.Model.API.PredictResult;

import java.io.Serializable;

public class PronosticsResult implements Serializable {

    private String id;

    @JsonProperty("percent_winning")
    private PredictResult result;

    @JsonProperty("home_goal_prediction")
    private String homeScore;

    @JsonProperty("away_goal_prediction")
    private String awayScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PredictResult getResult() {
        return result;
    }

    public void setResult(PredictResult result) {
        this.result = result;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }
}
