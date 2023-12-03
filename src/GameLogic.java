import java.util.Scanner;

public class GameLogic {
    private final String[] TREE_CHOICES = new String[]{"large", "medium", "small"};
    private final String[] OPTIONS = new String[]{"yes", "no"};
    private Scanner scan;
    private Game game;
    private boolean lastWeekEvent;

    public GameLogic() {
        game = new Game();
        scan = new Scanner(System.in);
        lastWeekEvent = false;
        // events = new RandomEvents();
        start();
    }

    private void start() {
        System.out.println("backstory");
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
        System.out.print("What would you like to do?\nType \"trees\" to buy trees\nType \"plots\" to buy plots\nType \"stats\" to see your current stats\n" +
                "Or \"end week\" to finish the week\ninput: ");
        String userInput = scan.nextLine().trim().toLowerCase();
        switch (userInput) {
            case "trees" -> newTree();
            case "plots" -> newPlot();
            case "stats" -> System.out.println(game.stats());
            case "end week" -> {
                System.out.println();
                RandomEvents events = new RandomEvents(lastWeekEvent);
                System.out.print(events.randomEventChooser());
                if (events.newEvent()) {
                    userInput = repeatUntil(OPTIONS);
                    if (userInput.compareTo("yes") == 0)
                    {
                        System.out.println(events.randomEventProcessor(userInput) + "\n");
                        game.moneyAfterEvent(events.moneyChange());
                    }
                    lastWeekEvent = true;
                } else {
                    lastWeekEvent = false;
                }
                game.newWeek();
            }
            default -> throw new IllegalStateException("Unexpected value: " + userInput); // use repeatuntil fucker
        }
    }


    private void newTree() {
        System.out.print("What kind of tree would you like?: ");
        String treeType = repeatUntil(TREE_CHOICES);
        System.out.print("How many trees would you like to purchase?: ");
        int amountWanted = 0;
        switch (treeType) {
            case "small" -> amountWanted = repeatUntil(10);
            case "medium" -> amountWanted = repeatUntil(7);
            case "large" -> amountWanted = repeatUntil(5);

            default -> throw new IllegalStateException("Unexpected value:" + treeType);
        }

        if (!game.canAffordTrees(treeType, amountWanted)) {
            System.out.println("You cannot afford " + amountWanted + " trees.");
            return;
        }

        System.out.print("Which plot would you like to plant it in?: ");
        int userPlotNum = repeatUntil(game.totalPlots()); // should always be at least 1
        if (!game.plotHasSpace(userPlotNum, treeType, amountWanted)) {
            System.out.println("Error, space not available in plot");
            return;
        }

        game.addTree(userPlotNum, treeType, amountWanted);
        System.out.println("Trees have been purchased!");
    }

    private boolean hasWon() {
        return false;
    }

    private void newPlot() {
        System.out.print("How many plots do you want to buy?: ");
        int amount = repeatUntil(99);
        if (game.canAffordPlots(amount)) {
            game.newPlot(amount);
        } else {
            System.out.println("Error, you do not have enough money to buy this many plots.");
        }
    }

    private boolean isInt(String checkingStr) {
        try {
            Integer.parseInt(checkingStr);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    private String repeatUntil(String[] strings) {
        String input = scan.nextLine().trim().toLowerCase();
        while (!objectInArray(strings, input)) {
            System.out.print("Error, please type in a valid response: ");
            input = scan.nextLine().trim().toLowerCase();
        }

        return input;
    }

    private int repeatUntil(int max) {
        String input = scan.nextLine().trim().toLowerCase();
        while (!isInt(input) || Integer.parseInt(input) < 1 || Integer.parseInt(input) > max) {
            System.out.print("Error, please type in an non-negative integer less than or equal to " + max + ": ");
            input = scan.nextLine().trim().toLowerCase();
        }

        return Integer.parseInt(input);
    }

    private boolean objectInArray(String[] strings, String string) {
        for (String arrayString : strings) {
            if (arrayString.equals(string)) {
                return true;
            }
        }
        return false;
    }
}
