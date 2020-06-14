package esgi.clicfootbackend.clicfootbackend.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Player implements Serializable {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    private String height;

    @Getter
    @Setter
    private String weight;

    @Getter
    @Setter
    private String nationality;

    @Getter
    @Setter
    private int number;

    @Getter
    @Setter
    private String team;

    @Getter
    @Setter
    private int goalCount;

    @Getter
    @Setter
    private int assistCount;

    @Getter
    @Setter
    private int yellowCardCount;

    @Getter
    @Setter
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
