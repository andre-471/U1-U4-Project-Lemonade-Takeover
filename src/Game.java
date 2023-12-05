import java.util.ArrayList;

public class Game {
    private double money;
    private ArrayList<Plot> plots;
    private int week;
    private String rank;
    private RandomEvent randomEvent;
    private boolean eventLastWeek;

    public Game() {
        money = 10000;
        plots = new ArrayList<Plot>();
        plots.add(new Plot());
        week = 0;
        rank = "local lemonade store";
        randomEvent = null;
        eventLastWeek = false;
    }


    public void newWeek() {
        week++;
        money += moneyPerWeek();
    }

    public String stats() {
        return "--- Week " + week + " ---\nRank: " + rank + "\nMoney: $" + money + "\nPlots: " + totalPlots();
    }

    public double netWorth() {
        double netWorth = 0;
        for (Plot plot : plots) {
            netWorth += plot.plotValue();
        }

        netWorth += money;

        return netWorth;
    }
    public boolean canAffordTrees(String treeType, int amount) {
        return money >= Tree.costBasedOnType(treeType) * amount;
    }

    public boolean canAffordPlots(int amount) {
        return money >= amount * Plot.COST;
    }

    public void buyPlot(int amount) {
        for (int i = 0; i < amount; i++) {
            plots.add(new Plot()); // subtract money
            money -= Plot.COST;
        }
    }

    public int totalPlots() {
        return plots.size();
    }

    public void updateRank() {

    }

    public String getPlotTrees(int plotNum) {
        return plots.get(plotNum - 1).listTrees();
    }

    public String getAllPlotTrees() {
        String l = "";
        for (int i = 0, j = plots.size(); i < j; i++) {
            l += getPlotTrees(i + 1);
            l += "\n";
        }

        return l;
    }

    public boolean plotHasSpace(int plotNum, String treeType, int amount) {
        return plots.get(plotNum - 1).hasSpace(treeType, amount);
    }

    public void makePlotSpace(int plotNum, String treeType, int amount) {
        plots.get(plotNum - 1).makePlotSpace(treeType, amount);
    }

    public void buyTree(int plotNum, String treeType, int amount) {
        plots.get(plotNum - 1).addTree(treeType, amount);
        money -= Tree.costBasedOnType(treeType) * amount;
    }

    public void sellTree(int plotNum, int treeNum) {
        int treeCost = plots.get(plotNum - 1).removeTree(treeNum - 1);
        money += treeCost;
    }

    public int moneyPerWeek() {
        int moneyGen = 0;
        for (Plot plot : plots) {
            moneyGen += plot.totalTreeProduction();
        }
        return moneyGen;
    }

    public boolean newRandomEvent() {
        randomEvent = new RandomEvent(eventLastWeek);
        eventLastWeek = !eventLastWeek;
        return randomEvent.ifEvent();
    }

    /***
     * PRECONDITION: randomEvent BETTER NOT BE (null) !!!!
     * @return a string related to the random event
     */
    public String randomEventPrompt() {
        return randomEvent.randomEventPrompt();
    }

    /***
     * PRECONDITION: randomEvent is not null
     * @return a string of the event message
     */
    public String processRandomEvent() {
        String eventMessage = randomEvent.processRandomEvent();
        money *= randomEvent.getMultiplier();
        roundMoney();
        return eventMessage;
    }

    private void roundMoney() {
        int intMoney = (int) money;
        double decimal = money - intMoney;
        decimal *= 100;
        decimal = (int) decimal;
        decimal /= 100;
        money = intMoney + decimal;
    }
}
