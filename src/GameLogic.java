import java.util.Scanner;

public class GameLogic {
    private final String[] MENU_CHOICES = new String[]{"trees", "plots", "stats", "see your trees", "end week"};
    private final String[] TREE_CHOICES = new String[]{"large", "medium", "small", "cancel"};
    private final String[] PLOT_CHOICES = new String[]{"empty","filled", "cancel"};
    private final String[] OPTIONS = new String[]{"yes", "no"};
    private Scanner scan;
    private Game game;

    public GameLogic() {
        game = new Game();
        scan = new Scanner(System.in);
        start();
    }

    private void start() {
        System.out.println("Ever since you were a wee youngin, you aspired to be a great capitalist, dominating the world in your image.\nYou smelt the opportunity of making great riches when you were bribing lab workers, and they talked about a new project\nGenetically modified lemon trees, that produce every week no matter the season.\nThis was it, the moment you've been waiting for.\n");
        System.out.println("Your goal to win is to become the face of lemonade, also known as acquire over a million dollars in net worth.\n");
        gameLoop();
    }

    private void gameLoop() {
        while (!hasWon()) {
            mainMenu();
        }
        System.out.println("Good Job!!! You won!!!! Good on you!!!!");
    }

    // game goes by week, gives player the option on what to do, random events first
    private void mainMenu() {
        System.out.println(game.stats()); // testing !!
        System.out.print("What would you like to do?\n" +
                "Type \"trees\" to buy trees\n" +
                "Type \"plots\" to buy plots\n" +
                "Type \"stats\" to see your current stats\n" +
                "Type \"see your trees\" to see them\n" +
                "Or \"end week\" to finish the week\n" +
                "input: ");
        String userInput = repeatUntil(MENU_CHOICES);
        switch (userInput) {
            case "buy trees" -> buyTree();
            case "sell trees" -> sellTree();
            case "plots" -> buyPlot();
            case "stats" -> System.out.println(game.stats());
            case "see your trees" -> System.out.println(game.getAllPlotTrees());
            case "end week" -> endWeek();
            default -> throw new IllegalStateException("Unexpected value: " + userInput); // this is illegal
        }
    }

    private void buyTree() {
        System.out.print("What kind of tree would you like? (Small, Medium, or Large or cancel): ");
        String treeType = repeatUntil(TREE_CHOICES);
        if (treeType.equals("cancel")) {
            return;
        }
        System.out.println("A plot can hold up to 10 small trees, 7 medium trees, or 5 large trees!");
        System.out.print("How many trees would you like to purchase?: ");
        // make sure you can't buy more than how many trees could fit in a plot
        int amountWanted = repeatUntil(0, Plot.maxTreesInPlot(treeType));

        if (!game.canAffordTrees(treeType, amountWanted)) {
            System.out.println("You cannot afford " + amountWanted + " trees.");
            return;
        }

        System.out.print("Which plot would you like to plant it in?: ");
        int userPlotNum = repeatUntil(1, game.totalPlots()); // should always be at least 1
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
        int plotAmount = repeatUntil(0, 100);
        if (plotType.equals("empty")) {
            if (game.canAffordPlots(plotAmount)) {
                game.chargeMoney(plotAmount);
                game.buyPlot(plotAmount);
            } else {
                System.out.println("Error, you do not have enough money to buy this many plots.");
            }
        } else {
            System.out.print("What kinds of trees would you like to buy?: ");
            String treeType = repeatUntil(TREE_CHOICES);
            System.out.print("How many of these trees would you like in each plot?: ");
            int treeAmount = repeatUntil(0, Plot.maxTreesInPlot(treeType));
            if (game.canAffordPlots(treeType,treeAmount,plotAmount)) {
                game.chargeMoney(treeType,treeAmount,plotAmount);
                game.newPlot(treeType, treeAmount, plotAmount);
            } else {
                System.out.println("Error, you do not have enough money to buy this many plots.");
            }
        }
    }

    private void sellTree() {
        System.out.println("choose plot");
        int userPlotNum = repeatUntil(1, game.totalPlots());

        System.out.println("choose to sell");
        int treeNum = repeatUntil(1, game.plotTreeCount(userPlotNum));

        game.sellTree(userPlotNum, treeNum);
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
        System.out.println("do you want clear space? ");
        System.out.print(game.treesRemovedIfMakeSpace(plotNum, treeType, amount));
        String userChoice = repeatUntil(OPTIONS);
        if ("no".equals(userChoice)) { return false; }

        game.makePlotSpace(plotNum, treeType, amount);

        return true;
    }

    private boolean hasWon() {
        return "the face of lemonade".equals(game.returnRank());
    }

    private String repeatUntil(String[] strings) {
        String input = scan.nextLine().trim().toLowerCase();
        while (!stringInArray(strings, input)) {
            System.out.print("Error, please type in a valid response: ");
            input = scan.nextLine().trim().toLowerCase();
        }

        return input;
    }

    private int repeatUntil(int min, int max) {
        String input = scan.nextLine().trim().toLowerCase();
        while (!isInt(input) || Integer.parseInt(input) < min || Integer.parseInt(input) > max) {
            System.out.print("Error, please type in an integer greater than or equal to " + min +
                    " and less than or equal to " + max + ": ");
            input = scan.nextLine().trim().toLowerCase();
        }

        return Integer.parseInt(input);
    }

    private boolean stringInArray(String[] strings, String string) {
        for (String arrayString : strings) {
            if (arrayString.equals(string)) {
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
