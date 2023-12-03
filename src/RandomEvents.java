public class RandomEvents {
    int event;
    boolean win; // to make decide if method adds or takes away money
    boolean lastWeekEvent;
    public RandomEvents(boolean lastWeekEvent) {
        event = 0;
        win = false;
        this.lastWeekEvent = lastWeekEvent;
    }

    public String randomEventChooser() {
        if (lastWeekEvent) {
            return "";
        }
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
    public boolean newEvent() {
        if (lastWeekEvent) {
            return false;
        }
        return event > 0;
    }
    public String randomEventProcessor(String userChoice) {
        return switch (event) {
            case 1 -> randomEvent1End(); // make a method that returns how much money should be changed by this
            case 2 -> randomEvent2End();
            case 3 -> randomEvent3End();
            case 4 -> randomEvent4End();
            case 5 -> randomEvent5End();
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
    private String randomEvent1End() {
        if (winOrLoss(5)) {
            win = true;
            return "You got away with putting drugs in your lemonade! You'll make some extra cash this week!";
        } else {
            return "Dang! You've been exposed and FDA fines you a lot of cash!";
        }
    }
    private String randomEvent2() {
        event = 2;
        return "Do you cut your worker's wages?\nYes or no?: ";
    }
    private String randomEvent2End() {
        if (winOrLoss(4)) {
            win = true;
            return "You aren't paying your workers a living wage!!!! You'll make some extra cash this week!";
        } else {
            return "You're getting punished as you should be! Pay your workers a living wage next time! The labor department is giving you a major fine.";
        }
    }
    private String randomEvent3() {
        event = 3;
        return "The Mafia is taking your trees! Do you fight back?\nYes or no?: ";
    }
    private String randomEvent3End() {
        if (winOrLoss(3)) {
            win = true;
            return "You actually beat the Mafia??? Good job you're going to get some great cash this week.";
        } else {
            return "You've been beat up and your cash has been stolen. Shouldn't have messed with the Mafia.";
        }
    }
    private String randomEvent4() {
        event = 4;
        return "Would you like to try a new advertising campaign?\nYes or no?: ";
    }
    private String randomEvent4End() {
        if (winOrLoss(8)) {
            win = true;
            return "Your ads were a great success! You'll be making some extra cash this week!";
        } else {
            return "What is wrong with you! Your ads freaking sucked everyone hated them.";
        }
    }
    private String randomEvent5() {
        event = 5;
        return "Do you buy sugar on the black market at a discount price?\nYes or no?: ";
    }
    private String randomEvent5End() {
        if (winOrLoss(9)) {
            win = true;
            return "No one will ever know! You'll be making some extra bucks from these dealings this week!";
        } else {
            return "The US is upset! Get ready for a fine!";
        }
    }
    private boolean winOrLoss(int win) {
        return ((int) (Math.random() * 11) + 1) > win;
    }
}
