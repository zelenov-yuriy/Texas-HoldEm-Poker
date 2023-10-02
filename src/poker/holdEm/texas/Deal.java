package poker.holdEm.texas;

public class Deal implements InterfaceDeal {
    private Deck deck;
    private Card[] flop;
    private Card turn;
    private Card river;

    public Deal(PlayersList pList) {
        deck = new Deck();
        deck.shuffle();
    }

    public int startDeal(PlayersList pList) {
        int a;
        int r;
        int cardNum;

        pList.setBlinds();
        pList.showChips();
        delimiter();
        pList.showButton();
        pList.showBlinds();
        delimiter();
        cardNum = pList.setHands(deck);
        pList.showUsersHand();
        delimiter();
        pList.setZeroBets();
        blindBets(pList);
        delimiter();
        preflopBets();
        pList.showBets();
        delimiter();
        pList.setPot();
        displayPot();
        pList.showPot();

        a = pList.getAllinPlayers();
        r = pList.getRemainPlayers();

        if ((a + r) > 1) {
            setFlop(cardNum);
            cardNum += 3;
            delimiter();
            cardsOnTable();
            showFlop(flop);
            if (r <= 1) {
                setTurn(cardNum++);
                showTurn(turn);
                setRiver(cardNum);
                showRiver(river);
                delimiter();
                showdownAnnounce();
                delimiter();
                pList.showdown();
                setCombinations(pList);
            } else {
                delimiter();
                pList.showUsersHand();
                delimiter();
                pList.setZeroBets();
                noBlindBets(pList);
                delimiter();
                flopBets();
                pList.showBets();
                delimiter();
                pList.setPot();
                displayPot();
                pList.showPot();

                a = pList.getAllinPlayers();
                r = pList.getRemainPlayers();

                if ((a + r) > 1) {
                    setTurn(cardNum++);
                    delimiter();
                    cardsOnTable();
                    showFlop(flop);
                    showTurn(turn);
                    if (r <= 1) {
                        setRiver(cardNum);
                        showRiver(river);
                        delimiter();
                        showdownAnnounce();
                        delimiter();
                        pList.showdown();
                        setCombinations(pList);
                    } else {
                        delimiter();
                        pList.showUsersHand();
                        pList.setZeroBets();
                        delimiter();
                        noBlindBets(pList);
                        delimiter();
                        turnBets();
                        pList.showBets();
                        delimiter();
                        pList.setPot();
                        displayPot();
                        pList.showPot();

                        a = pList.getAllinPlayers();
                        r = pList.getRemainPlayers();

                        if ((a + r) > 1) {
                            setRiver(cardNum);
                            delimiter();
                            cardsOnTable();
                            showFlop(flop);
                            showTurn(turn);
                            showRiver(river);
                            delimiter();
                            if (r <= 1) {
                                showdownAnnounce();
                                delimiter();
                                pList.showdown();
                                setCombinations(pList);
                            } else {
                                pList.showUsersHand();
                                pList.setZeroBets();
                                delimiter();
                                noBlindBets(pList);
                                delimiter();
                                riverBets();
                                pList.showBets();
                                delimiter();
                                pList.setPot();
                                displayPot();
                                pList.showPot();

                                a = pList.getAllinPlayers();
                                r = pList.getRemainPlayers();

                                if ((a + r) > 1) {
                                    delimiter();
                                    showdownAnnounce();
                                    delimiter();
                                    cardsOnTable();
                                    showFlop(flop);
                                    showTurn(turn);
                                    showRiver(river);
                                    delimiter();
                                    pList.showdown();
                                    setCombinations(pList);
                                }
                            }
                        }
                    }
                }
            }
        }
        delimiter();
        if ((a + r) > 1) {
            combinationsAnnounce();
            delimiter();
            pList.showCombinations();
            delimiter();
        }
        pList.setWinner();
        pList.resetPlayers();
        pList.shiftButton();
        delimiter();
        pList.showChips();
        delimiter();
        pList.thinPlayers();
        int n = pList.getNPlayers();
        return n;
    }

    public void blindBets(PlayersList pList) {
        boolean raise = true;
        int currentBet = 20;
        int tempBet = 0;
        Player temp = pList.bigBlind.getNext();
        Player point = pList.bigBlind;
        boolean shiftPoint = true;

        pList.smallBlind.setSmallBlind();
        pList.smallBlind.showBet();
        pList.bigBlind.setBigBlind();
        pList.bigBlind.showBet();

        do {
            if (pList.bigBlind.getNext() == pList.smallBlind) {
                if (pList.smallBlind.getAllIn()) {
                    raise = false;
                    continue;
                } else if (pList.bigBlind.getAllIn()) {
                    if (pList.bigBlind.getBet() <= pList.smallBlind.getBet()) {
                        raise = false;
                        continue;
                    } else {
                        currentBet = pList.bigBlind.getBet();
                    }
                }
            }
            if (!temp.getFold() && !temp.getAllIn()) {
                tempBet = temp.makeBet(currentBet);
                if (temp instanceof Bot)
                    temp.showBet();
            }
            if (!temp.getFold() && shiftPoint) {
                point = point.getNext();
                shiftPoint = false;
            }
            if (temp.getNext() == point)
                raise = false;
            if (tempBet > currentBet) {
                currentBet = tempBet;
                raise = true;
                point = temp;
            }
            temp = temp.getNext();
        } while (raise);
    }

    public void noBlindBets(PlayersList pList) {
        boolean raise = true;
        int currentBet = 0;
        int tempBet = 0;
        Player temp = pList.button.getNext();
        Player point;

        while (temp.getFold())
            temp = temp.getNext();
        point = temp;

        do {
            if (temp.getNext() == point)
                raise = false;
            if (!temp.getFold() && !temp.getAllIn()) {
                tempBet = temp.makeBet(currentBet);
                if (temp instanceof Bot)
                    temp.showBet();
            }
            if (tempBet > currentBet) {
                currentBet = tempBet;
                raise = true;
                point = temp;
            }
            temp = temp.getNext();
        } while (raise);
    }

    public void setFlop(int cardNum) {
        flop = new Card[3];
        flop[0] = deck.getCard(cardNum++);
        flop[1] = deck.getCard(cardNum++);
        flop[2] = deck.getCard(cardNum);
    }

    public void setTurn(int cardNum) {
        turn = deck.getCard(cardNum);
    }

    public void setRiver(int cardNum) {
        river = deck.getCard(cardNum);
    }

    public void setCombinations(PlayersList pList) {
        Player temp = pList.user;
        Card[] allCards;
        Combination[] variants;

        for (int i = 0; i < pList.nPlayers; i++) {
            if (!temp.getFold()) {
                allCards = new Card[7];
                for (int j = 0; j < 7; j++) {
                    allCards[0] = temp.getFirstCard();
                    allCards[1] = temp.getSecondCard();
                    allCards[2] = flop[0];
                    allCards[3] = flop[1];
                    allCards[4] = flop[2];
                    allCards[5] = turn;
                    allCards[6] = river;
                }
                variants = new Combination[21];
                setAllCombinations(allCards, variants);
                temp.setComb(variants);
            }
            temp = temp.getNext();
        }
    }

    public void setAllCombinations(Card[] allCards, Combination[] variants) {
        Card[][] five = new Card[21][5];

        int l = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 7; j++) {
                int m = 0;
                for (int k = 0; k < 7; k++) {
                    if ((k != i) && (k != j)) {
                        five[l][m] = allCards[k];
                        m++;
                    }
                }
                l++;
            }
        }
        for (int i = 0; i < 21; i++)
            variants[i] = new Combination(five[i]);
    }
}
