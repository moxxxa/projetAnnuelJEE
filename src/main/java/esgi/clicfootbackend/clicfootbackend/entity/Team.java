package esgi.clicfootbackend.clicfootbackend.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Team implements Serializable {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String image;

    @Getter
    @Setter
    private Player[] players;

    @Getter
    @Setter
    private int goalCount;

    public Team() {
        this.id = 2;
        this.name = "name";
        this.image = "image";
        this.players = new Player[0];
        this.goalCount = 1;
    }

}
