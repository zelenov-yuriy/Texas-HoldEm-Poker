package poker.holdEm.texas;

public class User extends Player implements InterfaceUser {
    private String userName;

    public User(String userName) {
        super();
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public int makeBet(int currentBet) {
        char choice;
        int raise;
        int dif;

        showYourChips(super.getChips());
        if (super.getBet() < currentBet) {
            if ((super.getBet() + super.getChips()) > currentBet) {
                if ((super.getNext().getNext() == this) && super.getNext().getAllIn())
                    choice = foldCall();
                else
                    choice = foldCallRaise();
                switch (choice) {
                    case 'f' -> {
                        super.setFold(true);
                        super.setPot(0);
                    }
                    case 'c' -> {
                        super.setChips(super.getChips() - (currentBet - super.getBet()));
                        super.setBet(currentBet);
                    }
                    case 'r' -> {
                        dif = currentBet - super.getBet();
                        raise = enterRaise(dif, super.getChips());
                        super.setChips(super.getChips() - raise);
                        super.setBet(super.getBet() + raise);
                        if (super.getChips() == 0) {
                            super.setAllIn(true);
                            super.setAllInBet(super.getBet());
                        }
                    }
                }
            } else if ((super.getBet() + super.getChips()) == currentBet) {
                choice = foldCall();
                switch (choice) {
                    case 'f' -> {
                        super.setFold(true);
                        super.setPot(0);
                    }
                    case 'c' -> {
                        super.setChips(0);
                        super.setAllIn(true);
                        super.setBet(currentBet);
                        super.setAllInBet(super.getBet());
                    }
                }
            } else {
                choice = foldAllin();
                switch (choice) {
                    case 'f' -> {
                        super.setFold(true);
                        super.setPot(0);
                    }
                    case 'a' -> {
                        super.setAllIn(true);
                        super.setBet(super.getBet() + super.getChips());
                        super.setChips(0);
                        super.setAllInBet(super.getBet());
                    }
                }
            }
        } else {
            if (super.getChips() != 0) {
                choice = checkRaise();
                switch (choice) {
                    case 'c':
                        break;
                    case 'r':
                        raise = enterRaise(super.getChips());
                        super.setChips(super.getChips() - raise);
                        super.setBet(super.getBet() + raise);
                        if (super.getChips() == 0) {
                            super.setAllIn(true);
                            super.setAllInBet(super.getBet());
                        }
                        break;
                }
            } else {
                youCheck();
            }
        }
        return super.getBet();
    }
}
