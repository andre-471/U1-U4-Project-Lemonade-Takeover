import java.util.Scanner;

public class GameIO {
    private Scanner scan = new Scanner(System.in);
    public GameIO() {}

    public void newTrees() {
        System.out.print("What kind of tree would you like?: ");
        String userInput = repeatUntil(new String[]{"large", "medium", "small"});
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
