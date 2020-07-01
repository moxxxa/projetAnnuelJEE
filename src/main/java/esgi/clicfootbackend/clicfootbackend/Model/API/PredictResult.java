package esgi.clicfootbackend.clicfootbackend.Model.API;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PredictResult implements Serializable {

    private String home;

    private String away;

    @JsonProperty("draws")
    private String draw;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }
}
