import java.util.Scanner;

public class GameLogic {
    private final String[] MENU_CHOICES = new String[]{"trees", "plots", "end week"};
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
    }

    private void gameLoop() {
        while (!hasWon()) {
            mainMenu();
        }
    }

    // game goes by week, gives player the option on what to do, random events first
    private void mainMenu() {
        System.out.println("$money -- #plots -- #trees\nbuy trees\nbuy plots\nend week");
        String userInput = repeatUntil(MENU_CHOICES);
        switch (userInput) {
            case "trees" -> newTree();
            case "plots" -> newPlot();
            case "end week" -> {
            }
            /* nextWeek() unimplemented method */
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    private void newTree() {
        System.out.print("What kind of tree would you like?: ");
        String treeType = repeatUntil(TREE_CHOICES);
        System.out.print("How many trees would you like to purchase?: ");
        // make an if statement checking for which tree choice it is to decide the max num of trees allowed
        int amountWanted = repeatUntil(10);
        if (game.canAffordTrees(amountWanted, treeType)) {
            System.out.println("Which plot would you like to plant it in?: ");
            int userPlotNum = repeatUntil(game.totalPlots()); // should always be at least 1
            if (game.plotHasSpace(userPlotNum, treeType)) {
                System.out.println("Trees have been purchased!");
            } else {
                System.out.println("Error, space not available in plot");
            }
        }
    }

    private boolean hasWon() {
        return false;
    }

    private void newPlot() {
        System.out.println("How many plots do you want to buy?: ");
        int userInput = repeatUntil(99);
        if (game.canAffordPlot(userInput)) {
            for (int i = 1; i < userInput; i++) {
                game.newPlot(userInput);
            }
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
            input = scan.nextLine().trim().toLowerCase();
            System.out.print("Error, please type in a valid response: ");
        }

        return input;
    }

    private int repeatUntil(int max) {
        String input = scan.nextLine().trim().toLowerCase();
        while (!isInt(input) && Integer.parseInt(input) <= 1 && Integer.parseInt(input) >= max) {
            input = scan.nextLine().trim().toLowerCase();
            System.out.print("Error, please type in an integer less than " + max + ": ");
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
