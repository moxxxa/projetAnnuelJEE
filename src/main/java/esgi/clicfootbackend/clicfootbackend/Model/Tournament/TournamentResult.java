package esgi.clicfootbackend.clicfootbackend.Model.Tournament;

public class TournamentResult {

    private String firstPlace;
    private String secondPlace;
    private String thirdPlace;

    private String firstPlacePrediction;
    private String secondPlacePrediction;
    private String thirdPlacePrediction;

    public TournamentResult(String firstPlace, String secondPlace, String thirdPlace, String firstPlacePrediction, String secondPlacePrediction, String thirdPlacePrediction) {
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.thirdPlace = thirdPlace;
        this.firstPlacePrediction = firstPlacePrediction;
        this.secondPlacePrediction = secondPlacePrediction;
        this.thirdPlacePrediction = thirdPlacePrediction;
    }

    //beacause we can have a tournament with only two teams
    public TournamentResult(String firstPlace, String secondPlace, String firstPlacePrediction, String secondPlacePrediction) {
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.firstPlacePrediction = firstPlacePrediction;
        this.secondPlacePrediction = secondPlacePrediction;
    }

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
