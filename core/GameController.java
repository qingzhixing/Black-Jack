package core;

public class GameController {
    GameController() {
        player = new HumanPlayer();
        dealer = new Dealer();
        cardController = new CardController();
        playerWinCounter = dealerWinCounter = 0;
    }

    GameController(int deckAmount, boolean enableWhiteCard, Player playerAI) {
        player = playerAI;
        dealer = new Dealer();
        cardController = new CardController(deckAmount, enableWhiteCard);
        playerWinCounter = dealerWinCounter = 0;
    }

    public void ShowCardsOnGame(){
        //TODO:complete
    }

    public void ShowCardsOutOfGame() {
        //TODO:complete
    }
    
    public void InitializeGame() {
        //TODO:complete
    }
    
    public void StartGame() {
        //TODO:complete
    }

    public void DisplayResult(Player player, Player dealer) {
        //TODO:complete
    }
    
    private Player player;
    private Player dealer;
    private CardController cardController;
    private int playerWinCounter;
    private int dealerWinCounter;
}
