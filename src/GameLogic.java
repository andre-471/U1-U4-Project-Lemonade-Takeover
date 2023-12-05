import java.util.Scanner;

public class GameLogic {
    private final String[] MENU_CHOICES = new String[]{"trees", "plots", "stats", "see trees", "end week"};
    private final String[] TREE_CHOICES = new String[]{"large", "medium", "small", "cancel"};
    private final String[] PLOT_CHOICES = new String[]{"empty", "filled", "cancel"};
    private final String[] OPTIONS = new String[]{"yes", "no"};
    private Scanner scan;
    private Game game;

    public GameLogic() {
        game = new Game();
        scan = new Scanner(System.in);
        start();
    }

    private void start() {
        System.out.println("Ever since you were a wee youngin, you aspired to be a great capitalist, dominating the world in your image." +
                "\nYou smelt the opportunity of making great riches when you were bribing lab workers, as they talked about a new project," +
                "\nGenetically modified lemon trees, that produce lemonade every week no matter the season." +
                "\nThis was it, the moment you've been waiting for.\n");
        System.out.println("Your goal to win is to become the face of lemonade, also known as acquire over a million dollars in net worth.\n");
        System.out.println("You took your single plot of land, your (legally) acquired lemon tree, and some spare pocket change\n" +
                "and got to work.");
        gameLoop();
    }

    private void gameLoop() {
        while (!game.hasWon() && !game.isDead()) {
            mainMenu();
        }
        if (game.hasWon()) {
            System.out.println("Good Job!!! You won!!!! Good on you!!!!");
        } else {
            game.dealWithDeath();
        }
    }

    // game goes by week, gives player the option on what to do, random events first
    private void mainMenu() {
        System.out.println();
        System.out.println(game.stats());
        System.out.print("What would you like to do?\n" +
                "Type \"trees\" to buy trees\n" +
                "Type \"plots\" to buy plots\n" +
                "Type \"stats\" to see your current stats\n" +
                "Type \"see trees\" to see them\n" +
                "Or \"end week\" to finish the week\n" +
                "input: ");
        String userInput = repeatUntil(MENU_CHOICES);
        switch (userInput) {
            case "trees" -> buyTree();
            case "plots" -> buyPlot();
            case "stats" -> System.out.println(game.detailedStats());
            case "see trees" -> System.out.println(game.listAllPlotTrees());
            case "end week" -> endWeek();
            default -> throw new IllegalStateException("Unexpected value: " + userInput); // this is illegal
        }
    }

    private void buyTree() {
        System.out.print("What kind of tree would you like? (small, medium, large, or cancel): ");
        String treeType = repeatUntil(TREE_CHOICES);
        if (treeType.equals("cancel")) {
            return;
        }
        System.out.println("A plot can hold up to 10 small trees, 7 medium trees, or 5 large trees!");
        System.out.print("How many trees would you like to purchase?: ");
        // make sure you can't buy more than how many trees could fit in a plot
        int amountWanted = repeatUntil(Plot.maxTreesInPlot(treeType));

        if (!game.canAffordTrees(treeType, amountWanted)) {
            System.out.println("You cannot afford " + amountWanted + " trees.");
            return;
        }

        System.out.print("Which plot would you like to plant it in?: ");
        int userPlotNum = repeatUntil(game.totalPlots());
        if (!game.plotHasSpace(userPlotNum, treeType, amountWanted) && !clearSpace(userPlotNum, treeType, amountWanted)) {
            System.out.println("Error, space not available in plot");
            return;
        }

        game.buyTree(userPlotNum, treeType, amountWanted);
        System.out.println("Trees have been purchased!");
    }


    private void buyPlot() {
        System.out.print("Would you like to buy an empty plot? Or one with trees? (empty or filled or cancel): ");
        String plotType = repeatUntil(PLOT_CHOICES);
        if (plotType.equals("cancel")) {
            return;
        }
        System.out.print("How many plots do you want to buy?: ");
        int plotAmount = repeatUntil(100);
        if (plotType.equals("empty")) {
            if (game.canAffordPlots(plotAmount)) {
                game.buyPlot(plotAmount);
                System.out.println("Plots have been purchased!");
            } else {
                System.out.println("Error, you do not have enough money to buy this many plots.");
            }
        } else {
            System.out.print("What kinds of trees would you like to buy?: ");
            String treeType = repeatUntil(TREE_CHOICES);
            System.out.print("How many of these trees would you like in each plot?: ");
            int treeAmount = repeatUntil(Plot.maxTreesInPlot(treeType));
            if (game.canAffordPlots(treeType, treeAmount, plotAmount)) {
                game.buyPlot(treeType, treeAmount, plotAmount);
                System.out.println("Plots have been purchased!");
            } else {
                System.out.println("Error, you do not have enough money to buy this many plots.");
            }
        }
    }

    private void endWeek() {
        System.out.println();
        if (game.newRandomEvent()) {
            System.out.print(game.randomEventPrompt());
            String userInput = repeatUntil(OPTIONS);
            if (userInput.equals("yes"))
            {
                System.out.println(game.processRandomEvent() + "\n");
            }
        }
        game.newWeek();
    }

    private boolean clearSpace(int plotNum, String treeType, int amount) {
        System.out.println("Your plot doesn't have enough space to add the trees you requested. ");
        System.out.println("Do you want to clear some space to add the new trees? ");
        System.out.println("These trees will be removed: ");
        System.out.println(game.treesRemovedIfMakeSpace(plotNum, treeType, amount));
        System.out.println("You will only get half of the money you paid for these trees back! (yes or no)");
        String userChoice = repeatUntil(OPTIONS);
        if ("no".equals(userChoice)) { return false; }

        game.makePlotSpace(plotNum, treeType, amount);

        return true;
    }

    private String repeatUntil(String[] strings) {
        String input = scan.nextLine().trim().toLowerCase();
        while (!stringInArray(strings, input)) {
            System.out.print("Error, please type in a valid response: ");
            input = scan.nextLine().trim().toLowerCase();
        }

        return input;
    }

    private int repeatUntil(int max) {
        String input = scan.nextLine().trim().toLowerCase();
        while (!isInt(input) || Integer.parseInt(input) < 1 || Integer.parseInt(input) > max) {
            System.out.print("Error, please type in a positive integer less than or equal to " + max + ": ");
            input = scan.nextLine().trim().toLowerCase();
        }

        return Integer.parseInt(input);
    }

    private boolean stringInArray(String[] strings, String string) {
        for (String arrayString : strings) {
            if (arrayString.compareTo(string) == 0) {
                return true;
            }
        }
        return false;
    }
    private boolean isInt(String checkingStr) {
        try {
            Integer.parseInt(checkingStr);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
