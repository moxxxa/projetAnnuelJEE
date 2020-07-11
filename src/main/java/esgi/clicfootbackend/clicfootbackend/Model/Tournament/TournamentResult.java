package esgi.clicfootbackend.clicfootbackend.Model.Tournament;

import java.io.Serializable;

public class TournamentResult implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String firstPlace;
    private String secondPlace;
    private String thirdPlace;

    private String firstPlacePrediction;
    private String secondPlacePrediction;
    private String thirdPlacePrediction;

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public String getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(String thirdPlace) {
        this.thirdPlace = thirdPlace;
    }

    public String getFirstPlacePrediction() {
        return firstPlacePrediction;
    }

    public void setFirstPlacePrediction(String firstPlacePrediction) {
        this.firstPlacePrediction = firstPlacePrediction;
    }

    public String getSecondPlacePrediction() {
        return secondPlacePrediction;
    }

    public void setSecondPlacePrediction(String secondPlacePrediction) {
        this.secondPlacePrediction = secondPlacePrediction;
    }

    public String getThirdPlacePrediction() {
        return thirdPlacePrediction;
    }

    public void setThirdPlacePrediction(String thirdPlacePrediction) {
        this.thirdPlacePrediction = thirdPlacePrediction;
    }
}
