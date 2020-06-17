package esgi.clicfootbackend.clicfootbackend.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Player implements Serializable {

    private int id;

    private String name;

    private int age;

    private String height;

    private String weight;

    private String nationality;

    private int number;

    private String team;

    private int goalCount;

    private int assistCount;

    private int yellowCardCount;

    private int redCardCount;

    public Player() {
        this.id = 1;
        this.name = "test";
        this.age = 25;
        this.height = "height";
        this.weight = "weight";
        this.nationality = "nationality";
        this.number = 27;
        this.team = "team";
        this.goalCount = 1;
        this.assistCount = 1;
        this.yellowCardCount = 1;
        this.redCardCount = 1;
    }
}
