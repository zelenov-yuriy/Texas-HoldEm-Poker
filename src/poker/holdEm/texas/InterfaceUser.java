package poker.holdEm.texas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface InterfaceUser {
    default void showChips(String userName, int chips) {
        System.out.println("Player " + userName + ": " + chips + " chips");
    }

    default void showHand(String userName, Card[] hand) {
        System.out.println(userName + "'s hand: ");
        System.out.println(hand[0]);
        System.out.println(hand[1]);
    }

    default void showCombination(String userName, Combination comb) {
        System.out.println("Player " + userName + "'s combination: ");
        System.out.println(comb);
    }

    default void showFold(String userName) {
        System.out.println("Player " + userName + ": fold");
    }

    default void showAllIn(String userName, int allInBet) {
        System.out.println("Player " + userName + "'s bet: " + allInBet + " (all in)");
    }

    default void showCheck(String userName) {
        System.out.println("Player " + userName + ": check");
    }

    default void showBet(String userName, int bet) {
        System.out.println("Player " + userName + "'s bet: " + bet);
    }

    default void showOneWinner(String userName, int pot) {
        System.out.println("Player " + userName + " takes " + pot + " pot!");
        System.out.println("Player " + userName + " gets " + pot + " chips!");
    }

    default void showTakedPot(String userName, int minPot) {
        System.out.println("Player " + userName + " takes " + minPot + " pot!");
    }

    default void showButton(String userName) {
        System.out.println("Button: Player " + userName);
    }

    default void showSmallBlind(String userName) {
        System.out.println("Small blind: Player " + userName);
    }

    default void showBigBlind(String userName) {
        System.out.println("Big blind: Player " + userName);
    }

    default void userGetsChips(String userName, int add) {
        System.out.println("Player " + userName + " gets " + add + " chips!");
    }

    default void userGetsRest(String userName, int rest) {
        System.out.println("Player " + userName + " gets " + rest + " rest!");
    }

    default void showYourChips(int chips) {
        System.out.println("Your chips: " + chips);
    }

    default char foldCallRaise() {
        char choice;

        System.out.println("You can fold, call or raise.");
        System.out.print("Enter the first letter of one of these variants: ");
        choice = getChar();
        while ((choice != 'f') && (choice != 'c') && (choice != 'r')) {
            System.out.println("Wrong input!");
            System.out.println("You can fold, call or raise.");
            System.out.print("Enter the first letter of one of these variants: ");
            choice = getChar();
        }
        return choice;
    }

    default int enterRaise(int dif, int chips) {
        int raise;

        System.out.print("Enter number of chips you want to add: ");
        raise = getInt();
        while ((raise <= dif) || (raise > chips)) {
            if (raise <= (dif + 1)) {
                System.out.println("The number is too small!");
                System.out.print("Enter number of chips you want to add: ");
                raise = getInt();
            } else {
                System.out.println("The number is too big!");
                System.out.print("Enter number of chips you want to add: ");
                raise = getInt();
            }
        }
        return raise;
    }

    default int enterRaise(int chips) {
        int raise;

        System.out.print("Enter number of chips you want to add: ");
        raise = getInt();
        while ((raise < 1) || (raise > chips)) {
            if (raise < 1) {
                System.out.println("The number is too small!");
                System.out.print("Enter number of chips you want to add: ");
                raise = getInt();
            } else {
                System.out.println("The number is too big!");
                System.out.print("Enter number of chips you want to add: ");
                raise = getInt();
            }
        }
        return raise;
    }

    default char foldCall() {
        char choice;

        System.out.println("You can fold or call.");
        System.out.print("Enter the first letter of one of these variants: ");
        choice = getChar();
        while ((choice != 'f') && (choice != 'c')) {
            System.out.println("Wrong input!");
            System.out.println("You can fold or call.");
            System.out.print("Enter the first letter of one of these variants: ");
            choice = getChar();
        }
        return choice;
    }

    default char foldAllin() {
        char choice;

        System.out.println("You can fold or all in.");
        System.out.print("Enter the first letter of one of these variants: ");
        choice = getChar();
        while ((choice != 'f') && (choice != 'a')) {
            System.out.println("Wrong input!");
            System.out.println("You can fold or all in.");
            System.out.print("Enter the first letter of one of these variants: ");
            choice = getChar();
        }
        return choice;
    }

    default char checkRaise() {
        char choice;

        System.out.println("You can check or raise.");
        System.out.print("Enter the first letter of one of these variants: ");
        choice = getChar();
        while ((choice != 'c') && (choice != 'r')) {
            System.out.println("Wrong input!");
            System.out.println("You can check or raise.");
            System.out.print("Enter the first letter of one of these variants: ");
            choice = getChar();
        }
        return choice;
    }

    default void youCheck() {
        System.out.println("You check.");
    }

    static String getString() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = "";
        try {
            s = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }

    static char getChar() {
        String s = getString();
        return s.charAt(0);
    }

    static int getInt() {
        String s = getString();
        return Integer.parseInt(s);
    }
}
