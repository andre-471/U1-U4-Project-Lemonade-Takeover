import java.util.Scanner;

public class GameIO {
    private final String[] USER_CHOICES = new String[]{"trees", "plots", "end week"};
    private final String[] TREE_CHOICES = new String[]{"large", "medium", "small"};
    private final char[] OPTIONS = new char[]{'y', 'n'};
    private Scanner scan = new Scanner(System.in);
    private Game game;

    public GameIO() {
        game = new Game();
    }

    public void start() {
        System.out.println("backstory");
    }

    // game goes by week, gives player the option on what to do, random events first
    public void mainMenu() {
        System.out.println("$money -- #plots -- #trees\nbuy trees\nbuy plots\nend week");
        String userInput = repeatUntil(USER_CHOICES);
        if (userInput.equals("trees")) {
            newTrees();
        } else if (userInput.equals("plots")) {
            /* newPlot() unimplemented method */
        } else if (userInput.equals("end week")) {
            /* nextWeek() unimplemented method */
        } else {
            throw new RuntimeException("Error, this shouldn't be possible to access");
        }

    }

    public void newTrees() {
        System.out.print("What kind of tree would you like?: ");
        String userInput = repeatUntil(TREE_CHOICES);
        System.out.println(userInput);
    }

    public void newPlot() {

    }

    private boolean checkInt(String checkingStr) {
        try {
            Integer.parseInt(checkingStr);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    private String repeatUntil(String[] strings) {
        String input = scan.nextLine();
        while (!objectInArray(strings, input)) {
            input = scan.nextLine();
            System.out.print("Error please try again: ");
        }

        return input.toLowerCase();
    }

    private char repeatUntil(char[] chars) {
        String input;

        do {
            input = scan.nextLine().trim();
        } while (input.length() != 1 && !objectInArray(chars, input.charAt(0)));

        return input.charAt(0);
    }

    private boolean objectInArray(String[] strings, String string) {
        for (String arrayString : strings) {
            if (arrayString.equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    private boolean objectInArray(char[] chars, char character) {
        for (char arrayChar : chars) {
            if (arrayChar == character) {
                return true;
            }
        }
        return false;
    }

}
