package esgi.clicfootbackend.clicfootbackend.Model.Team;


import esgi.clicfootbackend.clicfootbackend.Model.API.PlayerInfo;
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

    private ArrayList<PlayerInfo> players;

    private int goalCount;

    private int homeWin;

    private int homeLoses;

    private int homeDraws;

    private int outDraws;

    private int outWin;

    private int outLoses;

}
