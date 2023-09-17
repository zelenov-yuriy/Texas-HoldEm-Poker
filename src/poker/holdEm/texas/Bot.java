package poker.holdEm.texas;

public class Bot extends Player implements InterfaceBot {
    private int botNumber;

    public Bot(int botNumber) {
        super();
        this.botNumber = botNumber;
    }

    public int getBotNumber() {
        return botNumber;
    }

    @Override
    public int makeBet(int currentBet) {
        int choice;
        int raise;
        int dif;

        if (super.getBet() < currentBet) {
            if ((super.getBet() + super.getChips()) <= currentBet) {
                choice = (int) (Math.random() * 2);
                if (choice == 0) {
                    super.setFold(true);
                    super.setPot(0);
                } else {
                    super.setBet(super.getBet() + super.getChips());
                    super.setChips(0);
                    super.setAllIn(true);
                    super.setAllInBet(super.getBet());
                }
            } else {
                choice = (int) (Math.random() * 3);
                if (choice == 0) {
                    super.setFold(true);
                    super.setPot(0);
                } else if (choice == 1) {
                    super.setChips(super.getChips() - (currentBet - super.getBet()));
                    super.setBet(currentBet);
                    if (super.getChips() == 0) {
                        super.setAllIn(true);
                        super.setAllInBet(super.getBet());
                    }
                } else {
                    dif = currentBet - super.getBet();
                    raise = (int) (Math.random() * (super.getChips() - dif)) + dif + 1;
                    super.setChips(super.getChips() - raise);
                    super.setBet(super.getBet() + raise);
                    if (super.getChips() == 0) {
                        super.setAllIn(true);
                        super.setAllInBet(super.getBet());
                    }
                }
            }
        } else if (super.getChips() != 0) {
            choice = (int) (Math.random() * 2);
            if (choice == 0) {
                raise = (int) (Math.random() * super.getChips()) + 1;
                super.setChips(super.getChips() - raise);
                super.setBet(super.getBet() + raise);
                if (super.getChips() == 0) {
                    super.setAllIn(true);
                    super.setAllInBet(super.getBet());
                }
            }
        }
        return super.getBet();
    }
}
