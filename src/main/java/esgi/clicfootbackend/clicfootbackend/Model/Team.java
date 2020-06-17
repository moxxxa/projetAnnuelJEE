package esgi.clicfootbackend.clicfootbackend.Model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Team implements Serializable {

    private int id;

    private String name;

    private String image;

    private Player[] players;

    private int goalCount;

    public Team() {
        this.id = 2;
        this.name = "name";
        this.image = "image";
        this.players = new Player[0];
        this.goalCount = 1;
    }

}
