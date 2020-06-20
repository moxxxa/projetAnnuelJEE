package esgi.clicfootbackend.clicfootbackend.Model.Pronostics;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class PronosticsModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @NotNull
    @NotEmpty
    private String awayTeamId;

    @NotNull
    @NotEmpty
    private String awayTeamName;

    @NotNull
    @NotEmpty
    private String awayTeamLeagueId;

    @NotNull
    @NotEmpty
    private String awayTeamLeagueName;

    @NotNull
    @NotEmpty
    private String homeTeamId;

    @NotNull
    @NotEmpty
    private String homeTeamName;

    @NotNull
    @NotEmpty
    private String homeTeamLeagueId;

    @NotNull
    @NotEmpty
    private String date;

    @NotNull
    @NotEmpty
    private String imageHome;

    @NotNull
    @NotEmpty
    private String imageAway;

    private String homeResult;

    private String awayResult;

    private String drawResult;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getAwayTeamLeagueName() {
        return awayTeamLeagueName;
    }

    public void setAwayTeamLeagueName(String awayTeamLeagueName) {
        this.awayTeamLeagueName = awayTeamLeagueName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getHomeTeamLeagueName() {
        return homeTeamLeagueName;
    }

    public void setHomeTeamLeagueName(String homeTeamLeagueName) {
        this.homeTeamLeagueName = homeTeamLeagueName;
    }

    @NotNull
    @NotEmpty
    private String homeTeamLeagueName;

    
    public PronosticsModel(){ super();}

    public Long getId() {
        return id;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getAwayTeamLeagueId() {
        return awayTeamLeagueId;
    }

    public void setAwayTeamLeagueId(String awayTeamLeagueId) {
        this.awayTeamLeagueId = awayTeamLeagueId;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getHomeTeamLeagueId() {
        return homeTeamLeagueId;
    }

    public void setHomeTeamLeagueId(String homeTeamLeagueId) {
        this.homeTeamLeagueId = homeTeamLeagueId;
    }

    public String getHomeResult() {
        return homeResult;
    }

    public void setHomeResult(String homeResult) {
        this.homeResult = homeResult;
    }

    public String getAwayResult() {
        return awayResult;
    }

    public void setAwayResult(String awayResult) {
        this.awayResult = awayResult;
    }

    public String getDrawResult() {
        return drawResult;
    }

    public void setDrawResult(String drawResult) {
        this.drawResult = drawResult;
    }

    public String getImageHome() {
        return imageHome;
    }

    public void setImageHome(String imageHome) {
        this.imageHome = imageHome;
    }

    public String getImageAway() {
        return imageAway;
    }

    public void setImageAway(String imageAway) {
        this.imageAway = imageAway;
    }
}
