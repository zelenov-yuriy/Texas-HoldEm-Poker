package poker.holdEm.texas;

public class Deck {
    private Card[] cards;

    public Deck() {
        cards = new Card[52];
        cards[0] = new Card(0, "2", "Clubs");
        cards[1] = new Card(1, "2", "Diamonds");
        cards[2] = new Card(2, "2", "Hearts");
        cards[3] = new Card(3, "2", "Spades");
        cards[4] = new Card(4, "3", "Clubs");
        cards[5] = new Card(5, "3", "Diamonds");
        cards[6] = new Card(6, "3", "Hearts");
        cards[7] = new Card(7, "3", "Spades");
        cards[8] = new Card(8, "4", "Clubs");
        cards[9] = new Card(9, "4", "Diamonds");
        cards[10] = new Card(10, "4", "Hearts");
        cards[11] = new Card(11, "4", "Spades");
        cards[12] = new Card(12, "5", "Clubs");
        cards[13] = new Card(13, "5", "Diamonds");
        cards[14] = new Card(14, "5", "Hearts");
        cards[15] = new Card(15, "5", "Spades");
        cards[16] = new Card(16, "6", "Clubs");
        cards[17] = new Card(17, "6", "Diamonds");
        cards[18] = new Card(18, "6", "Hearts");
        cards[19] = new Card(19, "6", "Spades");
        cards[20] = new Card(20, "7", "Clubs");
        cards[21] = new Card(21, "7", "Diamonds");
        cards[22] = new Card(22, "7", "Hearts");
        cards[23] = new Card(23, "7", "Spades");
        cards[24] = new Card(24, "8", "Clubs");
        cards[25] = new Card(25, "8", "Diamonds");
        cards[26] = new Card(26, "8", "Hearts");
        cards[27] = new Card(27, "8", "Spades");
        cards[28] = new Card(28, "9", "Clubs");
        cards[29] = new Card(29, "9", "Diamonds");
        cards[30] = new Card(30, "9", "Hearts");
        cards[31] = new Card(31, "9", "Spades");
        cards[32] = new Card(32, "10", "Clubs");
        cards[33] = new Card(33, "10", "Diamonds");
        cards[34] = new Card(34, "10", "Hearts");
        cards[35] = new Card(35, "10", "Spades");
        cards[36] = new Card(36, "Jack", "Clubs");
        cards[37] = new Card(37, "Jack", "Diamonds");
        cards[38] = new Card(38, "Jack", "Hearts");
        cards[39] = new Card(39, "Jack", "Spades");
        cards[40] = new Card(40, "Queen", "Clubs");
        cards[41] = new Card(41, "Queen", "Diamonds");
        cards[42] = new Card(42, "Queen", "Hearts");
        cards[43] = new Card(43, "Queen", "Spades");
        cards[44] = new Card(44, "King", "Clubs");
        cards[45] = new Card(45, "King", "Diamonds");
        cards[46] = new Card(46, "King", "Hearts");
        cards[47] = new Card(47, "King", "Spades");
        cards[48] = new Card(48, "Ace", "Clubs");
        cards[49] = new Card(49, "Ace", "Diamonds");
        cards[50] = new Card(50, "Ace", "Hearts");
        cards[51] = new Card(51, "Ace", "Spades");
    }

    public void shuffle() {
        for (int i = 0; i < cards.length; i++) {
            int r = i + (int) (Math.random() * (cards.length - i));
            Card temp = cards[i];
            cards[i] = cards[r];
            cards[r] = temp;
        }
    }

    public Card getCard(int i) {
        return cards[i];
    }
}
