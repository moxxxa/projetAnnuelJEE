package esgi.clicfootbackend.clicfootbackend.Model.Pronostics;


public class PronosticsResult {

    private String home;
    private String away;

    private String draw;

    public PronosticsResult(String home, String away, String draw) {
        this.home = home;
        this.away = away;
        this.draw = draw;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }
}
