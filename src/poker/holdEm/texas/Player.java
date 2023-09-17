package poker.holdEm.texas;

public abstract class Player implements PlayerResults {
    private int chips;
    private int bet;
    private int allInBet;
    private Card[] hand;
    private boolean fold;
    private boolean allIn;
    private Combination comb;
    private Player next;
    private int pot;

    public Player() {
        chips = 1000;
        hand = new Card[2];
    }

    public int getChips() {
        return chips;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public Card getFirstCard() {
        return hand[0];
    }

    public Card getSecondCard() {
        return hand[1];
    }

    public Card[] getHand() {
        return hand;
    }

    public void setFirstCard(Card card) {
        hand[0] = card;
    }

    public void setSecondCard(Card card) {
        hand[1] = card;
    }

    public boolean getFold() {
        return fold;
    }

    public void setFold(boolean fold) {
        this.fold = fold;
    }

    public boolean getAllIn() {
        return allIn;
    }

    public void setAllIn(boolean allIn) {
        this.allIn = allIn;
    }

    public void setAllInBet(int allInBet) {
        this.allInBet = allInBet;
    }

    public Combination getComb() {
        return comb;
    }

    public void setComb(Combination[] variants) {
        if(variants == null)
            this.comb = null;
        else
            this.comb = determineBest(variants);
    }

    public Player getNext() {
        return next;
    }

    public void setNext(Player next) {
        this.next = next;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void setSmallBlind() {
        if (chips < 10) {
            bet = chips;
            chips = 0;
            allIn = true;
            allInBet = bet;
        } else {
            bet = 10;
            chips -= bet;
        }
    }

    public void setBigBlind() {
        if (chips < 20) {
            bet = chips;
            chips = 0;
            allIn = true;
            allInBet = bet;
        } else {
            bet = 20;
            chips -= bet;
        }
    }

    public void showOneWinner() {
        if (this instanceof Bot)
            ((Bot) this).showOneWinner(((Bot) this).getBotNumber(), this.pot);
        else if (this instanceof User)
            ((User) this).showOneWinner(((User) this).getUserName(), this.pot);
    }

    public void showChips() {
        if (this instanceof Bot)
            ((Bot) this).showChips(((Bot) this).getBotNumber(), this.chips);
        else if (this instanceof User)
            ((User) this).showChips(((User) this).getUserName(), this.chips);
    }

    public void showTakedPot(int minPot) {
        if (this instanceof Bot)
            ((Bot) this).showTakedPot(((Bot) this).getBotNumber(), minPot);
        else if (this instanceof User)
            ((User) this).showTakedPot(((User) this).getUserName(), minPot);
    }

    public void showButton() {
        if (this instanceof Bot)
            ((Bot) this).showButton(((Bot) this).getBotNumber());
        else if (this instanceof User)
            ((User) this).showButton(((User) this).getUserName());
    }

    public void showSmallBlind() {
        if (this instanceof Bot)
            ((Bot) this).showSmallBlind(((Bot) this).getBotNumber());
        else if (this instanceof User)
            ((User) this).showSmallBlind(((User) this).getUserName());
    }

    public void showBigBlind() {
        if (this instanceof Bot)
            ((Bot) this).showBigBlind(((Bot) this).getBotNumber());
        else if (this instanceof User)
            ((User) this).showBigBlind(((User) this).getUserName());
    }

    public void showBet() {
        if(fold) {
            if (this instanceof Bot)
                ((Bot) this).showFold(((Bot) this).getBotNumber());
            else if (this instanceof User)
                ((User) this).showFold(((User) this).getUserName());
        } else if(allIn) {
            if (this instanceof Bot)
                ((Bot) this).showAllIn(((Bot) this).getBotNumber(), allInBet);
            else if (this instanceof User)
                ((User) this).showAllIn(((User) this).getUserName(), allInBet);
        } else if(bet == 0) {
            if (this instanceof Bot)
                ((Bot) this).showCheck(((Bot) this).getBotNumber());
            else if (this instanceof User)
                ((User) this).showCheck(((User) this).getUserName());
        } else {
            if (this instanceof Bot)
                ((Bot) this).showBet(((Bot) this).getBotNumber(), this.bet);
            else if (this instanceof User)
                ((User) this).showBet(((User) this).getUserName(), this.bet);
        }
    }

    public void showFold() {
        if (this instanceof Bot)
            ((Bot) this).showFold(((Bot) this).getBotNumber());
        else if (this instanceof User)
            ((User) this).showFold(((User) this).getUserName());
    }

    public void showHand() {
        if (this instanceof Bot)
            ((Bot) this).showHand(((Bot) this).getBotNumber(), this.hand);
        else if (this instanceof User)
            ((User) this).showHand(((User) this).getUserName(), this.hand);
    }

    public void showCombination() {
        if (this instanceof Bot)
            ((Bot) this).showCombination(((Bot) this).getBotNumber(), this.comb);
        else if (this instanceof User)
            ((User) this).showCombination(((User) this).getUserName(), this.comb);
    }

    public void playerGetsChips(int add) {
        if (this instanceof Bot)
            ((Bot) this).botGetsChips(((Bot) this).getBotNumber(), add);
        else if (this instanceof User)
            ((User) this).userGetsChips(((User) this).getUserName(), add);
    }

    public void playerGetsRest(int rest) {
        if (this instanceof Bot)
            ((Bot) this).botGetsRest(((Bot) this).getBotNumber(), rest);
        else if (this instanceof User)
            ((User) this).userGetsRest(((User) this).getUserName(), rest);
    }

    public void addChips(int add) {
        chips += add;
    }

    public void addPot(int add) {
        pot += add;
    }

    public abstract int makeBet(int currentBet);
}
