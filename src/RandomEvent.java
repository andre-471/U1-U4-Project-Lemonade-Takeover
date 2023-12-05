public class RandomEvent {
    private int eventNumber;
    private boolean won; // to make decide if method adds or takes away money
    private boolean eventLastWeek;
    private double multiplier;
    public RandomEvent(boolean eventLastWeek) {
        won = false;
        this.eventLastWeek = eventLastWeek;
        eventNumber = (int)(Math.random() * 10) + 1;
        multiplier = 1;
    }

    public String randomEventPrompt() {
        return switch (eventNumber) {
            case 1 -> "Would you like to put drugs in your lemonade?\nYes or no?: ";
            case 2 -> "Do you cut your worker's wages?\nYes or no?: ";
            case 3 -> "The Mafia is taking your trees! Do you fight back?\nYes or no?: ";
            case 4 -> "Would you like to try a new advertising campaign?\nYes or no?: ";
            case 5 -> "Do you buy sugar on the black market at a discount price?\nYes or no?: ";
            default -> throw new IllegalStateException("Unexpected value: " + eventNumber);
        };
    }
    public boolean ifEvent() {
        if (eventLastWeek) {
            return false;
        }
        return eventNumber <= 5;
    }

    public boolean hasWon() {
        return won;
    }

    public double getMultiplier() {
        return multiplier;
    }
    public String processRandomEvent() {
        return switch (eventNumber) {
            case 1 -> randomEvent1(); // make a method that returns how much money should be changed by this
            case 2 -> randomEvent2();
            case 3 -> randomEvent3();
            case 4 -> randomEvent4();
            case 5 -> randomEvent5();
            default -> throw new IllegalStateException("Unexpected value: " + eventNumber);
        };
    }

    private String randomEvent1() {
        if (rollDice(5)) {
            won = true;
            multiplier = 1.2;
            return "You got away with putting drugs in your lemonade! You'll make some extra cash this week!";
        } else {
            multiplier = 0.8;
            return "Dang! You've been exposed and FDA fines you a lot of cash!";
        }
    }
    private String randomEvent2() {
        if (rollDice(4)) {
            won = true;
            multiplier = 1.1;
            return "You aren't paying your workers a living wage!!!! You'll make some extra cash this week!";
        } else {
            multiplier = 0.8;
            return "You're getting punished as you should be! Pay your workers a living wage next time! The labor department is giving you a major fine.";
        }
    }
    private String randomEvent3() {
        if (rollDice(3)) {
            won = true;
            multiplier = 1.1;
            return "You actually beat the Mafia??? Good job you're going to get some great cash this week.";
        } else {
            if (rollDice(10)) {
                // delete system 32
            }
            multiplier = 0.8;
            return "You've been beat up and your cash has been stolen. Shouldn't have messed with the Mafia.";
        }
    }
    private String randomEvent4() {
        if (rollDice(8)) {
            won = true;
            multiplier = 1.4;
            return "Your ads were a great success! You'll be making some extra cash this week!";
        } else {
            multiplier = 0.8;
            return "What is wrong with you! Your ads freaking sucked everyone hated them.";
        }
    }
    private String randomEvent5() {
        if (rollDice(9)) { // why are the odds so horrible for htis one
            won = true;
            multiplier = 1.5;
            return "No one will ever know! You'll be making some extra bucks from these dealings this week!";
        } else {
            multiplier = 0.7;
            return "The US is upset! Get ready for a fine!";
        }
    }
    private boolean rollDice(int minToWin) {
        return ((int) (Math.random() * 10) + 1) >= minToWin;
    }
}
