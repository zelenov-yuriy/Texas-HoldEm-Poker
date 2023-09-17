package poker.holdEm.texas;

public class Card {
    private int deckNumber;
    private String rank;
    private String suit;

    public Card(int deckNumber, String rank, String suit) {
        this.deckNumber = deckNumber;
        this.rank = rank;
        this.suit = suit;
    }

    public int getDeckNumber() {
        return deckNumber;
    }

    @Override
    public String toString() {
        return deckNumber + ") " + rank + " of " + suit;
    }
}
