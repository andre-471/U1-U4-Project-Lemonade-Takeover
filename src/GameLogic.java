import java.util.Scanner;

public class GameLogic {
    private final String[] MENU_CHOICES = new String[]{"trees", "plots", "stats", "end week"};
    private final String[] TREE_CHOICES = new String[]{"large", "medium", "small"};
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
        System.out.println("Your goal to win is to become the face of lemonade, also known as acquire over a million dollars in net worth.");
        gameLoop();
    }

    private void gameLoop() {
        while (!hasWon()) {
            mainMenu();
        }
    }

    // game goes by week, gives player the option on what to do, random events first
    private void mainMenu() {
        System.out.println(game.stats()); // testing !!
        System.out.println(game.getAllPlotTrees());
        System.out.print("What would you like to do?\n" +
                "Type \"trees\" to buy trees\n" +
                "Type \"plots\" to buy plots\n" +
                "Type \"stats\" to see your current stats\n" +
                "Or \"end week\" to finish the week\n" +
                "input: ");
        String userInput = repeatUntil(MENU_CHOICES);
        switch (userInput) {
            case "trees" -> newTree();
            case "plots" -> newPlot();
            case "stats" -> System.out.println(game.stats());
            case "end week" -> endWeek();
            default -> throw new IllegalStateException("Unexpected value: " + userInput); // this is illegal
        }
    }

    private void newTree() {
        System.out.print("What kind of tree would you like? (Small, Medium, or Large): ");
        String treeType = repeatUntil(TREE_CHOICES);
        System.out.println("A plot can hold up to 10 small trees, 7 medium trees, or 5 large trees!");
        System.out.print("How many trees would you like to purchase?: ");
        // make sures you can't buy more than how many trees could fit in a plot
        int amountWanted = repeatUntil(0, Plot.maxTreesInPlot(treeType));

        if (!game.canAfford(treeType, amountWanted)) {
            System.out.println("You cannot afford " + amountWanted + " trees.");
            return;
        }

        System.out.print("Which plot would you like to plant it in?: ");
        int userPlotNum = repeatUntil(1, game.totalPlots()); // should always be at least 1
        if (!game.plotHasSpace(userPlotNum, treeType, amountWanted) && !clearedSpace(userPlotNum, treeType, amountWanted)) {
            System.out.println("Error, space not available in plot");
            return;
        }

        game.chargeMoney(treeType,amountWanted);
        game.addTree(userPlotNum, treeType, amountWanted);
        System.out.println("Trees have been purchased!");
    }


    private void newPlot() {
        System.out.print("How many plots do you want to buy?: ");
        int amount = repeatUntil(0, 100);
        if (game.canAfford(amount)) {
            game.chargeMoney(amount);
            game.newPlot(amount);
        } else {
            System.out.println("Error, you do not have enough money to buy this many plots.");
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

    private boolean clearedSpace(int plotNum, String treeType, int amount) {
        System.out.print("do you want clear space? ");
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
