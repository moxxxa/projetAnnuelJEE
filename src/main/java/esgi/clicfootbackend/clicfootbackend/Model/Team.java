package esgi.clicfootbackend.clicfootbackend.Model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
public class Team implements Serializable {

    private int id;

    private String name;

    private String image;

    private ArrayList<Player> players;

    private int goalCount;

}
