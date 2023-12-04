import java.util.ArrayList;

public class Game {
    private double money;
    private ArrayList<Plot> plots;
    private int week;
    private String rank;

    public Game() {
        money = 10000;
        plots = new ArrayList<Plot>();
        plots.add(new Plot());
        week = 0;
        rank = "local lemonade store";
    }


    public void newWeek() {
        week++;
        money += MoneyPerWeek();
    }

    public String stats() {
        return "--- Week " + week + " ---\nRank: " + rank + "\nMoney: $" + money + "\nPlots: " + totalPlots();
    }

    public int netWorth() {
        int netWorth = 0;
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

    public void newPlot(int amount) {
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

    public void moneyAfterEvent(double multi) {
        money *= multi;
        money = roundMoney();
    }

    private double roundMoney() {
        int intMoney = (int) money;
        double decimal = money - intMoney;
        decimal *= 100;
        decimal = (int) decimal;
        decimal /= 100;
        return intMoney + decimal;
    }
}
