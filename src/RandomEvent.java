public class RandomEvent {
    private int eventNumber;
    private double multiplier;
    private boolean specialEvent;
    public RandomEvent() {
        eventNumber = (int)(Math.random() * 20) + 1;
        multiplier = 1;
        specialEvent = false;
    }

    public String randomEventPrompt() {
        return switch (eventNumber) {
            case 1 -> "Would you like to put drugs in your lemonade?\n" + winPercent(7) + "Yes or no?: ";
            case 2 -> "Do you cut your worker's wages?\n" + winPercent(6) + "Yes or no?: ";
            case 3 -> "The Mafia is taking your trees! Do you fight back?\n" + winPercent(8) + "Yes or no?: ";
            case 4 -> "Would you like to try a new advertising campaign?\n" + winPercent(3) + "Yes or no?: ";
            case 5 -> "Do you buy sugar on the black market at a discount price?\n" + winPercent(1) + " Yes or no?: ";
            default -> throw new IllegalStateException("Unexpected value: " + eventNumber);
        };
    }
    public boolean ifEvent() {
        return eventNumber <= 5;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public boolean isSpecialEvent() {
        return specialEvent;
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
        if (rollDice(7)) {
            multiplier = 1.2;
            return "You got away with putting drugs in your lemonade! You'll make some extra cash this week!";
        } else {
            multiplier = 0.8;
            return "Dang! You've been exposed and FDA fines you a lot of cash!";
        }
    }
    private String randomEvent2() {
        if (rollDice(6)) {
            multiplier = 1.1;
            return "You aren't paying your workers a living wage!!!! You'll make some extra cash this week!";
        } else {
            multiplier = 0.8;
            return "You're getting punished as you should be! Pay your workers a living wage next time! The labor department is giving you a major fine.";
        }
    }
    private String randomEvent3() {
        if (rollDice(8)) {
            multiplier = 1.1;
            return "You actually beat the Mafia??? Good job you're going to get some great cash this week.";
        } else {
            if (rollDice(10)) {
                specialEvent = true;
                return """
                                               uuuuuuuuuuuuuuuuuuuuu.
                                           .u$$$$$$$$$$$$$$$$$$$$$$$$$$W.
                                         u$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Wu.
                                       $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$i
                                      $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                                 `    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                                   .i$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$i
                                   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$W
                                  .$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$W
                                 .$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$i
                                 #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$.
                                 W$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                        $u       #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$~
                        $#      `"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                        $i        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                        $$        #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                        $$         $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                        #$.        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$#
                         $$      $iW$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$!
                         $$i      $$$$$$$#"" `""\"#$$$$$$$$$$$$$$$$$#""\"""\"#$$$$$$$$$$$$$$$W
                         #$$W    `$$$#"            "       !$$$$$`           `"#$$$$$$$$$$#
                          $$$     ``                 ! !iuW$$$$$                 #$$$$$$$#
                          #$$    $u                  $   $$$$$$$                  $$$$$$$~
                           "#    #$$i.               #   $$$$$$$.                 `$$$$$$
                                  $$$$$i.                ""\"#$$$$i.               .$$$$#
                                  $$$$$$$$!         .   `    $$$$$$$$$i           $$$$$
                                  `$$$$$  $iWW   .uW`        #$$$$$$$$$W.       .$$$$$$#
                                    "#$$$$$$$$$$$$#`          $$$$$$$$$$$iWiuuuW$$$$$$$$W
                                       !#""    ""             `$$$$$$$##$$$$$$$$$$$$$$$$
                                  i$$$$    .                   !$$$$$$ .$$$$$$$$$$$$$$$#
                                 $$$$$$$$$$`                    $$$$$$$$$Wi$$$$$$#"#$$`
                                 #$$$$$$$$$W.                   $$$$$$$$$$$#   ``
                                  `$$$$##$$$$!       i$u.  $. .i$$$$$$$$$#""
                                     "     `#W       $$$$$$$$$$$$$$$$$$$`      u$#
                                                    W$$$$$$$$$$$$$$$$$$      $$$$W
                                                    $$`!$$$##$$$$``$$$$      $$$$!
                                                   i$" $$$$  $$#"`  ""\"     W$$$$
                                                                           W$$$$!
                                              uW$$  uu  uu.  $$$  $$$Wu#   $$$$$$
                                             ~$$$$iu$$iu$$$uW$$! $$$$$$i .W$$$$$$
                                     ..  !   "#$$$$$$$$$$##$$$$$$$$$$$$$$$$$$$$#"
                                     $$W  $     "#$$$$$$$iW$$$$$$$$$$$$$$$$$$$$$W
                                     $#`   `       ""#$$$$$$$$$$$$$$$$$$$$$$$$$$$
                                                      !$$$$$$$$$$$$$$$$$$$$$#`
                                                      $$$$$$$$$$$$$$$$$$$$$$!
                                                    $$$$$$$$$$$$$$$$$$$$$$$`
                                                     $$$$$$$$$$$$$$$$$$$$"
                                     
                                  you died (idea wasn't copied trust me)""";
            }
            multiplier = 0.8;
            return "You've been beat up and your cash has been stolen. Shouldn't have messed with the Mafia.";
        }
    }
    private String randomEvent4() {
        if (rollDice(3)) {
            multiplier = 1.4;
            return "Your ads were a great success! You'll be making some extra cash this week!";
        } else {
            multiplier = 0.8;
            return "What is wrong with you! Your ads freaking sucked everyone hated them.";
        }
    }
    private String randomEvent5() {
        if (rollDice(1)) {
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
    private String winPercent(int minToWin) {  // im sorry mr miller this is the only way to meet the requirement
        String winPercent = "\nOdds of Losing\n";
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= minToWin; j++) {
                winPercent += "[10]";
            }
            winPercent += " = " + (minToWin * 10) + "% \n";
            minToWin = 10 - minToWin;
        }
        winPercent += "Odds of Winning\n";
        return winPercent;
    }
}
