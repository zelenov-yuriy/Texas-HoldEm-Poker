package poker.holdEm.texas;

public class Game implements InterfaceGame {
    private Deal deal;
    private PlayersList pList;
    private int dealNumber;

    public Game(int n, String userName) {
        pList = new PlayersList(n, userName);
        dealNumber = 1;
    }

    public boolean startGame() {
        boolean userLost = false;
        int n = pList.getNPlayers();

        while (!userLost && (n > 1)) {
            delimiter();
            delimiter();
            dealAnnounce(dealNumber);
            delimiter();
            deal = new Deal(pList);
            n = deal.startDeal(pList);
            userLost = pList.checkUser();
            dealNumber++;
        }
        return userLost;
    }

    public static void main(String[] args) {
        char again;
        int n;
        String userName = "";
        Game game = new Game(0, userName);
        boolean userLost;

        game.hello();
        do {
            n = game.enterN();
            userName = game.enterName();
            game = new Game(n, userName);
            userLost = game.startGame();
            if (!userLost) {
                game.youWon();
                game.delimiter();
            } else {
                game.youLost();
                game.delimiter();
            }
            again = game.playAgain();
        } while (again == 'y');
        game.delimiter();
        game.goodbye();
    }
}
