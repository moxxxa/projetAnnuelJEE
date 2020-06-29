package esgi.clicfootbackend.clicfootbackend.Model.Pronostics;

import esgi.clicfootbackend.clicfootbackend.Model.API.PredictResult;

import java.io.Serializable;

public class PronosticsResult implements Serializable {

    private String id;

    private PredictResult result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PredictResult getResult() {
        return result;
    }

    public void setResult(PredictResult result) {
        this.result = result;
    }
}
