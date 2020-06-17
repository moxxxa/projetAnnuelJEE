package esgi.clicfootbackend.clicfootbackend.Model.API;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class SearchResults implements Serializable {

    private int count;

    private ArrayList<SearchResult> results;

    public SearchResults(){
        this.count = 0;
        this.results = new ArrayList<SearchResult>();
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "count=" + count +
                ", results=" + results.toString() +
                '}';
    }
}

