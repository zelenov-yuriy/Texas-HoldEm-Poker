package poker.holdEm.texas;

public class Combination {
    private final Card[] cards;
    private final int priority;
    private final String strType;

    /*
     * priority - strType
     * -----------------
     * 0 - Highest Card
     * 1 - Pair
     * 2 - Two Pairs
     * 3 - Three
     * 4 - Straight
     * 5 - Flush
     * 6 - Full House
     * 7 - Four
     * 8 - Straight Flush
     */

    public Combination(Card[] cards) {
        this.cards = cards;
        sortCards();
        priority = combIdent();
        strType = typeIdent();
    }

    public int getPriority() {
        return priority;
    }

    public Card getCard(int n) {
        return cards[n];
    }

    @Override
    public String toString() {
        return strType + '\n'
                + cards[0].toString() + '\n'
                + cards[1].toString() + '\n'
                + cards[2].toString() + '\n'
                + cards[3].toString() + '\n'
                + cards[4].toString() + '\n';
    }

    public void sortCards() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < (4 - i); j++) {
                if (cards[j].getDeckNumber() > cards[j + 1].getDeckNumber()) {
                    Card temp = cards[j];
                    cards[j] = cards[j + 1];
                    cards[j + 1] = temp;
                }
            }
        }
    }

    private int combIdent() {
        if (isAPair()) {
            if (isATwoPairs()) {
                if (isAFullHouse())
                    return 6;
                return 2;
            } else if (isAThree()) {
                if (isAFour())
                    return 7;
                return 3;
            }
            return 1;
        } else if (isAStraight()) {
            if (isAFlush())
                return 8;
            return 4;
        } else if (isAFlush())
            return 5;
        return 0;
    }

    private boolean isAPair() {
        boolean result = false;
        for (int i = 0; i < 4; i++)
            if (cards[i].getDeckNumber() / 4 == cards[i + 1].getDeckNumber() / 4)
                result = true;
        return result;
    }

    private boolean isATwoPairs() {
        boolean result = false;
        for (int i = 0; i < 2; i++) {
            if (cards[i].getDeckNumber() / 4 == cards[i + 1].getDeckNumber() / 4) {
                for (int j = i + 2; j < 4; j++)
                    if (cards[j].getDeckNumber() / 4 == cards[j + 1].getDeckNumber() / 4)
                        result = true;
            }
        }
        return result;
    }

    private boolean isAThree() {
        boolean result = false;
        for (int i = 0; i < 3; i++)
            if (cards[i].getDeckNumber() / 4 == cards[i + 1].getDeckNumber() / 4 &&
                    cards[i + 1].getDeckNumber() / 4 == cards[i + 2].getDeckNumber() / 4)
                result = true;
        return result;
    }

    private boolean isAStraight() {
        boolean result = true;
        if (cards[0].getDeckNumber() / 4 < 9) {
            for (int i = 0; i < 4; i++)
                if (cards[i + 1].getDeckNumber() / 4 != cards[i].getDeckNumber() / 4 + 1)
                    result = false;
            if (cards[0].getDeckNumber() / 4 == 0 && cards[1].getDeckNumber() / 4 == 1 &&
                    cards[2].getDeckNumber() / 4 == 2 && cards[3].getDeckNumber() / 4 == 3 &&
                    cards[4].getDeckNumber() / 4 == 12)
                result = true;
        }
        return result;
    }

    private boolean isAFlush() {
        boolean result = true;
        for (int i = 0; i < 4; i++)
            if (cards[i].getDeckNumber() % 4 != cards[i + 1].getDeckNumber() % 4)
                result = false;
        return result;
    }

    private boolean isAFullHouse() {
        boolean result = false;
        if ((cards[0].getDeckNumber() / 4 == cards[1].getDeckNumber() / 4) &&
                (cards[1].getDeckNumber() / 4 == cards[2].getDeckNumber() / 4))
            result = true;
        else if ((cards[2].getDeckNumber() / 4 == cards[3].getDeckNumber() / 4) &&
                (cards[3].getDeckNumber() / 4 == cards[4].getDeckNumber() / 4))
            result = true;
        return result;
    }

    private boolean isAFour() {
        boolean result = false;
        if ((cards[0].getDeckNumber() / 4 == cards[1].getDeckNumber() / 4) &&
                (cards[1].getDeckNumber() / 4 == cards[2].getDeckNumber() / 4) &&
                (cards[2].getDeckNumber() / 4 == cards[3].getDeckNumber() / 4))
            result = true;
        else if ((cards[1].getDeckNumber() / 4 == cards[2].getDeckNumber() / 4) &&
                (cards[2].getDeckNumber() / 4 == cards[3].getDeckNumber() / 4) &&
                (cards[3].getDeckNumber() / 4 == cards[4].getDeckNumber() / 4))
            result = true;
        return result;
    }

    private String typeIdent() {
        return switch (priority) {
            case 1 -> "Pair";
            case 2 -> "Two Pairs";
            case 3 -> "Three";
            case 4 -> "Straight";
            case 5 -> "Flush";
            case 6 -> "Full House";
            case 7 -> "Four";
            case 8 -> "Straight Flush";
            default -> "Highest Card";
        };
    }
}
