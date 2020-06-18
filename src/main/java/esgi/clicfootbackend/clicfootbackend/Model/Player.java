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
    }
}
