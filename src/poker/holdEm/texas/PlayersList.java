package poker.holdEm.texas;

public class PlayersList implements InterfaceGame {
    User user;
    Player button;
    Player smallBlind;
    Player bigBlind;
    int nPlayers;
    boolean userLost;

    public PlayersList(int n, String userName) {
        user = new User(userName);
        Player temp1 = user;
        for (int i = 1; i < n; i++) {
            Player temp2 = new Bot(i);
            temp1.setNext(temp2);
            temp1 = temp2;
            if (i == (n - 1))
                temp1.setNext(user);
        }
        int buttonNum = (int) (Math.random() * n);
        temp1 = user;
        for (int i = 0; i < n; i++) {
            if (i == buttonNum) {
                button = temp1;
                break;
            }
            temp1 = temp1.getNext();
        }
        nPlayers = n;
    }

    public void setBlinds() {
        if (nPlayers == 2) {
            smallBlind = button;
            bigBlind = smallBlind.getNext();
        } else {
            smallBlind = button.getNext();
            bigBlind = smallBlind.getNext();
        }
    }

    public int setHands(Deck deck) {
        int cardNum = 0;
        Player temp;

        if (nPlayers == 2)
            temp = bigBlind;
        else
            temp = smallBlind;
        for (int i = 0; i < nPlayers; i++) {
            temp.setFirstCard(deck.getCard(cardNum++));
            temp.setSecondCard(deck.getCard(cardNum++));
            temp = temp.getNext();
        }
        return cardNum;
    }

    public void setZeroBets() {
        Player temp = user;

        for (int i = 0; i < nPlayers; i++) {
            temp.setBet(0);
            temp = temp.getNext();
        }
    }

    public void setPot() {
        Player temp1 = user;
        Player temp2 = user;

        for (int i = 0; i < nPlayers; i++) {
            for (int j = 0; j < nPlayers; j++) {
                if (temp1.getFold())
                    break;
                if (temp1.getBet() < temp2.getBet())
                    temp1.addPot(temp1.getBet());
                else
                    temp1.addPot(temp2.getBet());
                temp2 = temp2.getNext();
            }
            temp1 = temp1.getNext();
        }
    }

    public void setWinner() {
        Player temp;
        int remain = nPlayers;

        temp = user;
        for (int i = 0; i < nPlayers; i++) {
            if (temp.getFold())
                remain--;
            temp = temp.getNext();
        }
        if (remain > 1)
            setSomePots();
        else
            setOnePot();
    }

    public void setOnePot() {
        Player temp = user;

        for (int i = 0; i < nPlayers; i++) {
            if (!temp.getFold()) {
                temp.showOneWinner();
                temp.addChips(temp.getPot());
            }
            temp = temp.getNext();
        }
    }

    public void setSomePots() {
        int[] pots = new int[nPlayers];
        Combination[] combs = new Combination[nPlayers];
        boolean[] winners = new boolean[nPlayers];
        int nPots;
        boolean unique;
        Player temp = user;
        int remainPot;

        for (int i = 0; i < nPlayers; i++) {
            if (!temp.getFold()) {
                pots[i] = temp.getPot();
                combs[i] = temp.getComb();
            }
            temp = temp.getNext();
        }
        nPots = 0;
        for (int i = 0; i < nPlayers; i++) {
            if (pots[i] != 0) {
                unique = true;
                for (int j = 0; j < i; j++)
                    if (pots[i] == pots[j])
                        unique = false;
                if (unique)
                    nPots++;
            }
        }
        while (nPots > 0) {
            TableResults.determineWinCombs(winners, combs);
            remainPot = sharePots(winners, pots);
            if (remainPot == 0)
                break;
            else {
                thinWinners(winners, pots);
                thinCombs(winners, combs);
                nPots--;
            }
        }
    }

    public int sharePots(boolean[] winners, int[] pots) {
        int minPot = 0;
        int nWinners = 0;
        int add;
        int rest;
        Player temp;
        int remainPot = 0;

        for (int i = 0; i < pots.length; i++) {
            if (winners[i]) {
                if (minPot == 0)
                    minPot = pots[i];
                else if (pots[i] < minPot)
                    minPot = pots[i];
            }
        }
        for (int i = 0; i < winners.length; i++)
            if (winners[i])
                nWinners++;
        showSomeWinners(minPot, winners);
        add = minPot / nWinners;
        rest = minPot % nWinners;

        temp = user;
        for (int i = 0; i < nPlayers; i++) {
            if (winners[i]) {
                temp.addChips(add);
                temp.playerGetsChips(add);
            }
            pots[i] -= minPot;
            temp = temp.getNext();
        }
        temp = button.getNext();
        for (int i = 0; i < nPlayers; i++) {
            if (winners[i]) {
                temp.addChips(rest);
                if (rest > 0)
                    temp.playerGetsRest(rest);
                break;
            }
            temp = temp.getNext();
        }

        for (int i = 0; i < pots.length; i++)
            if (pots[i] > remainPot)
                remainPot = pots[i];

        return remainPot;
    }

    public void thinWinners(boolean[] winners, int[] pots) {
        for (int i = 0; i < winners.length; i++) {
            if (pots[i] == 0)
                winners[i] = false;
            else if (pots[i] > 0)
                winners[i] = true;
        }
    }

    public void thinCombs(boolean[] winners, Combination[] combs) {
        for (int i = 0; i < winners.length; i++)
            if (!winners[i])
                combs[i] = null;
    }

    public int getNPlayers() {
        return nPlayers;
    }

    public int getAllinPlayers() {
        int allIn = 0;
        Player temp = user;

        for (int i = 0; i < nPlayers; i++) {
            if (temp.getAllIn())
                allIn++;
            temp = temp.getNext();
        }
        return allIn;
    }

    public int getRemainPlayers() {
        int remain = 0;
        Player temp = user;

        for (int i = 0; i < nPlayers; i++) {
            if (!temp.getFold() && (temp.getChips() != 0))
                remain++;
            temp = temp.getNext();
        }
        return remain;
    }

    public void showChips() {
        Player temp = user;

        for (int i = 0; i < nPlayers; i++) {
            temp.showChips();
            temp = temp.getNext();
        }
    }

    public void showButton() {
        button.showButton();
    }

    public void showBlinds() {
        smallBlind.showSmallBlind();
        bigBlind.showBigBlind();
    }

    public void showUsersHand() {
        user.showHand(user.getUserName(), user.getHand());
    }

    public void showBets() {
        Player temp = user;

        for (int i = 0; i < nPlayers; i++) {
            temp.showBet();
            temp = temp.getNext();
        }
    }

    public void showPot() {
        int[] sPot = new int[nPlayers];
        Player temp = user;
        int n;
        int in;

        for (int i = 0; i < sPot.length; i++) {
            sPot[i] = temp.getPot();
            temp = temp.getNext();
        }
        for (int out = 1; out < sPot.length; out++) {
            n = sPot[out];
            in = out;
            while ((in > 0) && (sPot[in - 1] > n)) {
                sPot[in] = sPot[in - 1];
                in--;
            }
            sPot[in] = n;
        }
        showPot(sPot);
    }

    public void showdown() {
        Player temp = user;

        for (int i = 0; i < nPlayers; i++) {
            if (temp.getFold())
                temp.showFold();
            else
                temp.showHand();
            temp = temp.getNext();
            if (i != (nPlayers - 1))
                emptyString();
        }
    }

    public void showCombinations() {
        Player temp = user;

        for (int i = 0; i < nPlayers; i++) {
            if (temp.getFold())
                temp.showFold();
            else
                temp.showCombination();
            temp = temp.getNext();
            if (i != (nPlayers - 1))
                emptyString();
        }
    }

    public void showSomeWinners(int minPot, boolean[] winners) {
        boolean comma = false;
        int nWinners = 0;
        Player temp = user;

        for (int i = 0; i < winners.length; i++)
            if (winners[i])
                nWinners++;

        if (nWinners == 1) {
            for (int i = 0; i < winners.length; i++) {
                if (winners[i])
                    break;
                temp = temp.getNext();
            }
            temp.showTakedPot(minPot);
        } else {
            System.out.print("Players ");
            for (int i = 0; i < winners.length; i++) {
                if (winners[i]) {
                    if (comma)
                        System.out.print(", ");
                    if (temp instanceof User)
                        System.out.print(((User) temp).getUserName());
                    else
                        System.out.print(((Bot) temp).getBotNumber());
                    comma = true;
                }
                temp = temp.getNext();
            }
            System.out.println(" share " + minPot + " pot!");
        }
    }

    public void resetPlayers() {
        Player temp = user;
        for (int i = 0; i < nPlayers; i++) {
            temp.setFold(false);
            temp.setAllIn(false);
            temp.setComb(null);
            temp.setPot(0);
            temp = temp.getNext();
        }
    }

    public void shiftButton() {
        do
            button = button.getNext();
        while (button.getChips() == 0);
    }

    public void thinPlayers() {
        Player prev = user;
        Player temp = prev.getNext();
        int newNum = nPlayers;

        for (int i = 0; i < nPlayers; i++) {
            if (temp.getChips() == 0) {
                newNum--;
                if (temp instanceof User)
                    userLost = true;
                prev.setNext(temp.getNext());
            } else
                prev = prev.getNext();
            temp = temp.getNext();
        }
        nPlayers = newNum;
    }

    public boolean checkUser() {
        return userLost;
    }
}
