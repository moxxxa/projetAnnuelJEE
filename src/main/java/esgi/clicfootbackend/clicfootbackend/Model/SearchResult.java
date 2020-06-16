package esgi.clicfootbackend.clicfootbackend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;

@Getter
@Setter
public class SearchResult implements Serializable {

    private int count;

    private String[] results;

    public SearchResult(@JsonProperty("count") int count, @JsonProperty("results") String[] results){
        this.count = count;
        this.results = results;
    }

    public SearchResult(){
        this.count = 0;
        this.results = new String[0];
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "count=" + count +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
