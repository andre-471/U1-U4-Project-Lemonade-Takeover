import java.util.ArrayList;

public class Game {
    private double money;
    private ArrayList<Plot> plots;
    private int week;
    private String rank;
    private RandomEvent randomEvent;
    private boolean eventLastWeek;

    public Game() {
        money = 1000;
        plots = new ArrayList<Plot>();
        plots.add(new Plot());
        plots.get(0).addTree("small",1);
        week = 0;
        rank = "local lemonade store";
        randomEvent = null;
        eventLastWeek = false;
    }

    public String returnRank() {
        return rank;
    }
    public void newWeek() {
        week++;
        money += MoneyPerWeek();
        updateRank();
    }

    public String stats() {
        return "--- Week " + week + " ---\nRank: " + rank + "\nMoney: $" + money + "\nPlots: " + totalPlots();
    }

    public double netWorth() {
        int netWorth = 0;
        for (Plot plot : plots) {
            netWorth += plot.plotValue();
        }

        netWorth += money;

        return netWorth;
    }
    public boolean canAfford(String treeType, int amount) {
        return money >= Tree.costBasedOnType(treeType) * amount;
    }

    public boolean canAfford(int amount) {
        return money >= amount * Plot.COST;
    }
    public void chargeMoney(int amount) {
        money -= amount * Plot.COST;
    }
    public void chargeMoney(String treeType, int amount) {
        money -= Tree.costBasedOnType(treeType) * amount;
    }

    public void newPlot(int amount) {
        for (int i = 0; i < amount; i++) {
            plots.add(new Plot()); // subtract money
            money -= Plot.COST;
        }
    }

    public int totalPlots() {
        return plots.size();
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

    public void addTree(int plotNum, String treeType, int amount) {
        plots.get(plotNum - 1).addTree(treeType, amount);
    }

    public int MoneyPerWeek() {
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
    private void updateRank() {
        double netWorth = netWorth();
        if (netWorth < 50000) {
            rank = "local lemonade store";
        } else if (netWorth < 100000) {
            rank = "regional lemonade chain";
        } else if (netWorth < 250000) {
            rank = "national lemonade chain";
        } else if (netWorth < 1000000) {
            rank = "world wide lemonade chain";
        } else {
            rank = "the face of lemonade";
        }
    }
}
