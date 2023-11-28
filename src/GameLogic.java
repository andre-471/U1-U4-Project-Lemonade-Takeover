import java.util.Scanner;

public class GameLogic {
    private final String[] MENU_CHOICES = new String[]{"trees", "plots", "end week", "stats"};
    private final String[] TREE_CHOICES = new String[]{"large", "medium", "small"};
    private final String[] OPTIONS = new String[]{"y", "n"};
    private Scanner scan;
    private Game game;

    public GameLogic() {
        game = new Game();
        scan = new Scanner(System.in);
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

        System.out.print("What would you like to do?\nType \"trees\" to buy trees\nType \"plots\" to buy plots\nType \"stats\" to see your current stats\n" +
                "Or \"end week\" to finish the week\ninput: ");
        String userInput = repeatUntil(MENU_CHOICES);
        switch (userInput) {
            case "trees" -> newTree();
            case "plots" -> newPlot();
            case "stats" -> System.out.println(game.stats());
            case "end week" -> {
                game.newWeek();
            }
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }


    private void newTree() {
        System.out.print("What kind of tree would you like?: ");
        String treeType = repeatUntil(TREE_CHOICES);
        System.out.print("How many trees would you like to purchase?: ");

        int amountWanted = repeatUntil(10);
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
