import java.util.Scanner;

public class GameLogic {
    private final String[] USER_CHOICES = new String[]{"trees", "plots", "end week"};
    private final String[] TREE_CHOICES = new String[]{"large", "medium", "small"};
    private final String[] OPTIONS = new String[]{"y", "n"};
    private Scanner scan;
    private Game game;

    public GameLogic() {
        game = new Game();
        scan = new Scanner(System.in);
    }

    public void start() {
        System.out.println("backstory");
    }

    // game goes by week, gives player the option on what to do, random events first
    public void mainMenu() {
        System.out.println("$money -- #plots -- #trees\nbuy trees\nbuy plots\nend week");
        String userInput = repeatUntil(USER_CHOICES);
        if (userInput.equals("trees")) {
            newTree();
        } else if (userInput.equals("plots")) {
            newPlot();
        } else if (userInput.equals("end week")) {
            /* nextWeek() unimplemented method */
        } else {
            throw new IllegalStateException("Unexpected value: " + userInput);
        }

    }

    public void newTree() {
        System.out.print("What kind of tree would you like?: ");
        String userInput = repeatUntil(TREE_CHOICES);
        System.out.print("What plot # do you want to add the tree to?: ");
        int plotNum = repeatUntil(game.totalPlots()); // should always be at least 1

    }

    public void newPlot() {

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
            System.out.print("Error please try again: ");
        }

        return input;
    }

    private int repeatUntil(int max) {
        String input = scan.nextLine().trim().toLowerCase();
        while (!isInt(input) && Integer.parseInt(input) <= 1 && Integer.parseInt(input) >= max) {
            input = scan.nextLine().trim().toLowerCase();
            System.out.print("Error please try again: ");
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
