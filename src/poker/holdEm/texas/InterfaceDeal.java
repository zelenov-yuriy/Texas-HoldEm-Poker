package poker.holdEm.texas;

public interface InterfaceDeal {
    default void delimiter() {
        System.out.println("------------------------------------------------------------");
    }

    default void preflopBets() {
        System.out.println("Preflop Bets: ");
    }

    default void displayPot() {
        System.out.println("Pot: ");
    }

    default void cardsOnTable() {
        System.out.println("Cards on the table: ");
    }

    default void showFlop(Card[] flop) {
        for (int i = 0; i < flop.length; i++)
            System.out.println(flop[i]);
        ;
    }

    default void showTurn(Card turn) {
        System.out.println(turn);
    }

    default void showRiver(Card river) {
        System.out.println(river);
        ;
    }

    default void showdownAnnounce() {
        System.out.println("                          Showdown");
    }

    default void flopBets() {
        System.out.println("Flop bets: ");
    }

    default void turnBets() {
        System.out.println("Turn bets: ");
    }

    default void riverBets() {
        System.out.println("River bets: ");
    }

    default void combinationsAnnounce() {
        System.out.println("                        Combinations");
    }
}
