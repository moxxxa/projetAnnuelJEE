package esgi.clicfootbackend.clicfootbackend.Model;

import esgi.clicfootbackend.clicfootbackend.Model.API.PlayerInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Player implements Serializable {

    private PlayerInfo info;

    private int age;

    private String height;

    private String weight;

    private int number;

    private String team;

    private int goalCount;

    private int assistCount;

    private int yellowCardCount;

    private int redCardCount;

    public Player() {
    }
}
