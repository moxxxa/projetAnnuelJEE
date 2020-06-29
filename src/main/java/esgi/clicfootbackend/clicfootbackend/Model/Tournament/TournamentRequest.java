package esgi.clicfootbackend.clicfootbackend.Model.Tournament;

import java.io.Serializable;

public class TournamentRequest implements Serializable {

    private String id;

    private String[] teamsId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getTeamsId() {
        return teamsId;
    }

    public void setTeamsId(String[] teamsId) {
        this.teamsId = teamsId;
    }
}
