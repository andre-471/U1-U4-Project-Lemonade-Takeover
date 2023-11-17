import java.util.ArrayList;

public class Game {
    private double money;
    private ArrayList<Plot> plots;
    private int week;

    public Game() {
        plots = new ArrayList<Plot>();
        week = 0;
    }

    public void newPlot() {
        plots.add(new Plot());
    }

    public void newWeek() {
        week++;
        randomEvent();
    }

    public void randomEvent() {

    }

    public int totalPlots() {
        return plots.size();
    }


}
