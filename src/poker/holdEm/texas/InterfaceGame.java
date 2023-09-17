package poker.holdEm.texas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface InterfaceGame {
    default void delimiter() {
        System.out.println("------------------------------------------------------------");
    }

    default void emptyString() {
        System.out.println();
    }

    default void hello() {
        System.out.println("Hello! Welcome to Texas Hold'em Poker!");
    }

    default int enterN() {
        int n;

        System.out.print("Enter number of players: ");
        n = getInt();
        while ((n < 2) || (n > 10)) {
            System.out.println("Wrong input!");
            System.out.print("Enter number of players (from 2 to 10): ");
            n = getInt();
        }
        return n;
    }

    default String enterName() {
        String userName;

        System.out.print("Enter your name: ");
        userName = getString();
        return userName;
    }

    default void dealAnnounce(int dealNumber) {
        System.out.println("                          Deal №" + dealNumber);
    }

    default void youWon() {
        System.out.println("You won! Other players have no chips!");
    }

    default void youLost() {
        System.out.println("You lost! You have no chips!");
    }

    default char playAgain() {
        char again;

        System.out.print("Would you like to play again? (yes/no): ");
        again = getChar();
        while ((again != 'n') && (again != 'y')) {
            System.out.println("Wrong input!");
            System.out.print("Would you like to play again? (yes/no): ");
            again = getChar();
        }
        return again;
    }

    default void goodbye() {
        System.out.println("Goodbye! See you soon!");
    }

    default void showPot(int[] sPot) {
        boolean add = false;
        int temp = 0;

        for (int i = 0; i < sPot.length; i++) {
            if (sPot[i] == 0)
                continue;
            if (!add) {
                System.out.println(sPot[i]);
                add = true;
                temp = sPot[i];
            } else {
                if (sPot[i] > temp) {
                    System.out.println("Additional pot: ");
                    System.out.println(sPot[i] - temp);
                    temp = sPot[i];
                }
            }
        }
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
