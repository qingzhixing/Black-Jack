package core;

import java.util.ArrayList;

public class GameController {
    public static enum GameState {
        GameGoing,
        GameOver,
    }
    
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

    public void ShowCards() {
        System.out.println("Player: " + player.GetCardsString());
        switch (gameState) {
            case GameGoing:
                System.out.println("Dealer: "
                        + dealer.GetCardsStringHideFirst());
                break;
            case GameOver:
                System.out.println("Dealer: "
                        + dealer.GetCardsString());
                break;
        }
    }
    
    public void InitializeGame() {
        gameState = GameState.GameGoing;
        cardController.InitializeCards();
    }
    
    public void StartGame() {
        int loops;
        if (player instanceof AIPlayer) {
            
        }
    }

    public void DisplayResult(Player player, Player dealer) {
        //TODO:complete
    }
    
    private Player player;
    private Dealer dealer;
    private CardController cardController;
    private int playerWinCounter;
    private int dealerWinCounter;
    private GameState gameState;
}
