package esgi.clicfootbackend.clicfootbackend.Model.Tournament;

import com.sun.istack.NotNull;
import esgi.clicfootbackend.clicfootbackend.enums.ResultStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
@Entity
public class TournamentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private ResultStatus status;

    @NotNull
    @NotEmpty
    private String token;

    @NotNull
    @NotEmpty
    private String date;

    @ElementCollection
    private List<Integer> tournament = new ArrayList<Integer>();;


    private String firstPlace;
    private String secondPlace;
    private String thirdPlace;

    private String firstPlacePrediction;
    private String secondPlacePrediction;
    private String thirdPlacePrediction;

    public TournamentModel(){ super();}

    public Long getId() {
        return id;
    }

    public List<Integer> getTournament() {
        return tournament;
    }

    public void setTournament(List<Integer> tournament) {
        this.tournament = tournament;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
