public class RandomEvents {
    int event;
    boolean win; // to make decide if method adds or takes away money
    public RandomEvents() {
        event = 0;
    }

    public String randomEventChooser() {
        int num = (int)(Math.random() * 10);
        return switch (num) {
            case 1 -> randomEvent1();
            case 2 -> randomEvent2();
            case 3 -> randomEvent3();
            case 4 -> randomEvent4();
            case 5 -> randomEvent5();
            default -> "";
        };
    }
    public String randomEventProcessor(String userChoice) {
        return switch (event) {
            case 1 -> proccessRandomEvent1(userChoice); // make a method that returns how much money should be changed by this
            case 2 -> proccessRandomEvent2(userChoice);
            case 3 -> proccessRandomEvent3(userChoice);
            case 4 -> proccessRandomEvent4(userChoice);
            case 5 -> proccessRandomEvent5(userChoice);
            default -> throw new RuntimeException("error processing event");
        };
    }
    public double moneyChange() {
        if (win) {
            return Math.random() + 1;
        } else {
            return Math.random();
        }
    }
    private String randomEvent1() {
        event = 1;
        return "Would you like to put drugs in your lemonade?\nYes or no?: ";
    }
    private String randomEvent2() {
        event = 2;
        return "Do you cut your worker's wages?\nYes or no?: ";
    }
    private String randomEvent3() {
        event = 3;
        return "The Mafia is taking your trees! Do you fight back?\nYes or no?: ";
    }
    private String randomEvent4() {
        event = 4;
        return "Would you like to try a new advertising campaign?\nYes or no?: ";
    }
    private String randomEvent5() {
        event = 5;
        return "Do you buy sugar on the black market at a discount price?\nYes or no?: ";
    }

}
