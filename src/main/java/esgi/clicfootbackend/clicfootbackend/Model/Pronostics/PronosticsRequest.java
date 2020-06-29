package esgi.clicfootbackend.clicfootbackend.Model.Pronostics;

import java.io.Serializable;

public class PronosticsRequest implements Serializable {

    private String id;

    private String homeTeamId;

    private String awayTeamId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
}
