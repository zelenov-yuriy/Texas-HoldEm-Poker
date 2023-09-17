package poker.holdEm.texas;

public interface InterfaceBot {
    default void showChips(int botNumber, int chips) {
        System.out.println("Player " + botNumber + ": " + chips + " chips");
    }

    default void showHand(int botNumber, Card[] hand) {
        System.out.println("Player " + botNumber + "'s hand: ");
        System.out.println(hand[0]);
        System.out.println(hand[1]);
    }

    default void showCombination(int botNumber, Combination comb) {
        System.out.println("Player " + botNumber + "'s combination: ");
        System.out.println(comb);
    }

    default void showFold(int botNumber) {
        System.out.println("Player " + botNumber + ": fold");
    }

    default void showAllIn(int botNumber, int allInBet) {
        System.out.println("Player " + botNumber + "'s bet: " + allInBet + " (all in)");
    }

    default void showCheck(int botNumber) {
        System.out.println("Player " + botNumber + ": check");
    }

    default void showBet(int botNumber, int bet) {
        System.out.println("Player " + botNumber + "'s bet: " + bet);
    }

    default void showOneWinner(int botNumber, int pot) {
        System.out.println("Player " + botNumber + " takes " + pot + " pot!");
        System.out.println("Player " + botNumber + " gets " + pot + " chips!");
    }

    default void showTakedPot(int botNumber, int minPot) {
        System.out.println("Player " + botNumber + " takes " + minPot + " pot!");
    }

    default void showButton(int botNumber) {
        System.out.println("Button: Player " + botNumber);
    }

    default void showSmallBlind(int botNumber) {
        System.out.println("Small blind: Player " + botNumber);
    }

    default void showBigBlind(int botNumber) {
        System.out.println("Big blind: Player " + botNumber);
    }

    default void botGetsChips(int botNumber, int add) {
        System.out.println("Player " + botNumber + " gets " + add + " chips!");
    }

    default void botGetsRest(int botNumber, int rest) {
        System.out.println("Player " + botNumber + " gets " + rest + " rest!");
    }
}
