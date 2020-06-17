package esgi.clicfootbackend.clicfootbackend.Model.API;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SearchResult implements Serializable {

    private int id;

    private String name;

    public SearchResult(int id, String name){
        this.id = id;
        this.name = name;
    }

}
