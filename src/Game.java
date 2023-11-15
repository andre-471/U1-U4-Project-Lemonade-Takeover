import java.util.ArrayList;

public class Game {
    private double money;
    private ArrayList<Plot> plots;
    private int week;
    private GameIO io;

    public Game() {
        plots = new ArrayList<Plot>();
        week = 1;
        io = new GameIO();
    }

    public void newWeek() {
        if (week > 1) {
            /* random event method not yet constructed */
        }
        io.mainMenu();

    }
}
