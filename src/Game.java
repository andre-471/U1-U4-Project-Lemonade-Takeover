import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
// https://www.w3schools.com/java/java_files_create.asp

public class Game {
    private double money;
    private double netWorth;
    private ArrayList<Plot> plots;
    private int week;
    private String rank;
    private RandomEvent randomEvent;
    private boolean eventLastWeek;
    private boolean dead;

    public Game() {
        money = 100;
        netWorth = money + Plot.BASE_COST + Tree.costBasedOnType("small");
        plots = new ArrayList<Plot>();
        plots.add(new Plot());
        plots.get(0).addTree("small",1);
        week = 1;
        rank = "local lemonade store";
        randomEvent = null;
        eventLastWeek = false;
        dead = false;
    }

    public boolean hasWon() {
        return "the face of lemonade".equals(rank);
    }

    public void newWeek() {
        if (dead) { return; }
        week++;
        money += moneyPerWeek();
        netWorth += moneyPerWeek();
        updateRank();
    }

    public String stats() {
        return "--- Week " + week + " ---\nRank: " + rank + "\nMoney: $" + money + "\nPlots: " + totalPlots();
    }

    public String detailedStats() {
        return "Current Rank: " + rank + "\n" +
                "Current Money: $" + money + "\n" +
                "Total Net Worth: $" + netWorth + "\n" +
                "Total # of Plots: " + totalPlots() + "\n" +
                "Total # of Trees: " + listAllPlotTreesWithType();
    }

    public boolean isDead() {
        return dead;
    }

    public void dealWithDeath() {
//        try {
//            File myObj = new File("%temp%\\filename.txt");
//            if (myObj.createNewFile()) {
//                System.out.println("File created: " + myObj.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
    }

    public boolean canAffordTrees(String treeType, int amount) {
        return money >= Tree.costBasedOnType(treeType) * amount;
    }

    public boolean canAffordPlots(int amount) {
        return money >= amount * Plot.BASE_COST;
    }
    public boolean canAffordPlots(String treeType, int treeAmount, int plotAmount) {
        return money >= (plotAmount * Plot.BASE_COST) + (Tree.costBasedOnType(treeType) * treeAmount * plotAmount);
    }

    public void chargeMoney(int plotAmount) {
        money -= plotAmount * Plot.BASE_COST;
        roundMoney();
    }
    public void chargeMoney(String treeType, int treeAmount) {
        money -= Tree.costBasedOnType(treeType) * treeAmount;
        roundMoney();
    }
    public void chargeMoney(String treeType, int treeAmount, int plotAmount) {
        money -= (plotAmount * Plot.BASE_COST) + (Tree.costBasedOnType(treeType) * treeAmount * plotAmount);
        roundMoney();
    }

    public void buyPlot(int amount) {
        chargeMoney(amount);
        for (int i = 0; i < amount; i++) {
            plots.add(new Plot());
        }
    }

    public void buyPlot(String treeType, int treeAmount, int plotAmount) {
        chargeMoney(treeType, treeAmount, plotAmount);
        for (int i = 0; i < plotAmount; i++) {
            plots.add(new Plot(treeType, treeAmount));
        }
    }
    public void buyTree(int plotNum, String treeType, int amount) {
        plots.get(plotNum - 1).addTree(treeType, amount);
        chargeMoney(treeType, amount);
    }

    public int totalPlots() {
        return plots.size();
    }

    public String listAllPlotTreesWithType() {
        int small = 0, medium = 0, large = 0, total = 0;
        for (Plot plot : plots) {
            int[] treeCount = plot.countTreeTypes();
            small += treeCount[0];
            medium += treeCount[1];
            large += treeCount[2];
        }

        return "S: " + small + " M: " + medium + " L: " + large + " (Total: " + (small + medium + large) + ")";
    }

    public String listAllPlotTrees() {
        StringBuilder l = new StringBuilder();
        for (int i = 0, j = plots.size(); i < j; i++) {
            l.append(i + 1);
            l.append(": ");
            l.append(plots.get(i).listTrees());
            l.append("\n");
        }

        return l.toString();
    }


    public boolean plotHasSpace(int plotNum, String treeType, int amount) {
        return plots.get(plotNum - 1).hasSpace(treeType, amount);
    }

    public void makePlotSpace(int plotNum, String treeType, int amount) {
        money += plots.get(plotNum - 1).makePlotSpace(treeType, amount);
        roundMoney();
    }

    public String treesRemovedIfMakeSpace(int plotNum, String treeType, int amount) {
        int[] treesRemoved = plots.get(plotNum - 1).treesRemovedIfMakeSpace(treeType, amount);
        return "remove " + treesRemoved[0] + " small\n" +
                treesRemoved[1] + " medium\n" +
                treesRemoved[2] + " large";
    }


    public int moneyPerWeek() {
        int moneyGen = 0;
        for (Plot plot : plots) {
            moneyGen += plot.totalTreeProduction();
        }
        return moneyGen;
    }

    public boolean newRandomEvent() {
        if (eventLastWeek) {
            eventLastWeek = false;
            return false;
        }
        randomEvent = new RandomEvent();
        eventLastWeek = true;
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
        if (randomEvent.isSpecialEvent()) {
            dead = true;
        }
        money *= randomEvent.getMultiplier();
        roundMoney();
        randomEvent = null;
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
