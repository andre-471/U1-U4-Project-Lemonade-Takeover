import java.util.Scanner;

public class GameIO {
    private final String[] USER_CHOICES = new String[]{"trees", "plots", "end week"};
    private final String[] TREE_CHOICES = new String[]{"large", "medium", "small"};
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

    }



    public void newTrees() {
        System.out.print("What kind of tree would you like?: ");
        String userInput = repeatUntil(TREE_CHOICES);
        System.out.println(userInput);
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
        while (!stringInArray(strings, input)) {
            input = scan.nextLine();
            System.out.print("Error please try again: ");
        }

        return input;
    }

    private boolean stringInArray(String[] strings, String string) {
        for (String arrayString : strings) {
            if (arrayString.equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }
}
