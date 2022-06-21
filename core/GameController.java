package core;

import java.util.Scanner;
//TODO:白卡判定

public class GameController {
    public static enum GameState {
        GameGoing,
        GameOver,
    }
    
    public GameController() {
        player = new HumanPlayer();
        dealer = new Dealer();
        cardController = new CardController();
        playerWinCounter = dealerWinCounter = drawCounter = 0;
    }

    public GameController(int deckAmount, boolean enableWhiteCard, Player playerAI) {
        player = playerAI;
        dealer = new Dealer();
        cardController = new CardController(deckAmount, enableWhiteCard);
        playerWinCounter = dealerWinCounter = drawCounter = 0;
    }

    private void ShowCards() {
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
        InitializeGame();
        Scanner scanner = new Scanner(System.in);
        int loops = 1;
        if (player instanceof AIPlayer) {
            System.out.println("How many loops do you want AI play?");
            loops = scanner.nextInt();
        }
        boolean breakable = true;
        while (loops > 0 && breakable) {
            breakable = true;

            player.AddCard(cardController.SendCard());
            player.AddCard(cardController.SendCard());
            dealer.AddCard(cardController.SendCard());
            dealer.AddCard(cardController.SendCard());

            ShowCards();

            System.out.println("---player round---");
            while (player.MakeDecision(dealer.GetVisibleCard()) == Player.DecisionType.HIT) {
                System.out.println("Player hit");
                player.AddCard(cardController.SendCard());
                ShowCards();
            }
            System.out.println("Player stand");

            System.out.println("---dealer round---");
            while (dealer.MakeDecision(dealer.GetVisibleCard()) == Player.DecisionType.HIT) {
                System.out.println("Dealer hit");
                dealer.AddCard(cardController.SendCard());
                ShowCards();
            }
            System.out.println("Dealer stand");

            DisplayResult(player, dealer);

            if (player instanceof HumanPlayer) {
                System.out.println("Do you want to play again? (y/n)");
                String answer = scanner.next();
                if (answer.equals("y")) {
                    breakable = false;
                }
            }
            loops--;
        }
        
        System.out.println("---Game Over---");
        System.out.printf("Player won %d times\n", playerWinCounter);
        System.out.printf("Dealer won %d times\n", dealerWinCounter);
        System.out.printf("Draw %d times\n", drawCounter);
        if (playerWinCounter == 0) {
            System.out.println("The probability of a player winning is 0%");
        }else if (dealerWinCounter == 0) {
            System.out.println("The probability of a player winning is 100%");
        } else {
            System.out.printf("The probability of a player winning is %.2f%%\n",
                    (double) playerWinCounter / (playerWinCounter + dealerWinCounter) * 100);
        }

        scanner.close();
    }

    public void DisplayResult(Player player, Player dealer) {
        if (player.CalculatePossiblePointSum().size() == 0) {
            System.out.println("The winner is Dealer!");
            dealerWinCounter++;
            return;
        }
        if (dealer.CalculatePossiblePointSum().size() == 0) {
            System.out.println("The winner is Player!");
            playerWinCounter++;
            return;
        }
        int playerCardsSum = 0;
        int dealerCardsSum = 0;
        for (int item : player.CalculatePossiblePointSum()) {
            playerCardsSum = Math.max(playerCardsSum, item);
        }
        for (int item : dealer.CalculatePossiblePointSum()) {
            dealerCardsSum = Math.max(dealerCardsSum, item);
        }
        if (playerCardsSum > dealerCardsSum) {
            System.out.println("The winner is Player!");
            playerWinCounter++;
        } else if (playerCardsSum < dealerCardsSum) {
            System.out.println("The winner is Dealer!");
            dealerWinCounter++;
        } else {
            System.out.println("Even!There is no winner!");
            drawCounter++;
        }

    }
    
    private Player player;
    private Dealer dealer;
    private CardController cardController;
    private int playerWinCounter;
    private int dealerWinCounter;
    private int drawCounter;
    private GameState gameState;
}
