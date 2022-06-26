package core;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class GameController {
    public enum GameState {
        GameGoing,
        GameOver,
    }

    public enum ResultType{
        Win,
        Lose,
        Draw,
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

    public void StartGame() {
        cardController.InitializeCards();
        Scanner scanner = new Scanner(System.in);
        int loops = 1;
        if (player instanceof AIPlayer) {
            System.out.println("How many loops do you want AI play?");
            if(scanner.hasNext()) {
                loops= scanner.nextInt();
            }
        }
        boolean breakable = true;
        while (loops > 0 || !breakable) {
            gameState = GameState.GameGoing;
            breakable = true;

            player.cards.clear();
            dealer.cards.clear();

            player.AddCard(cardController.SendCard());
            player.AddCard(cardController.SendCard());
            dealer.AddCard(cardController.SendCard());
            dealer.AddCard(cardController.SendCard());

            ShowCards();

            System.out.println("---player round---");
            while (player.MakeDecision(dealer.GetVisibleCard()) == Player.DecisionType.HIT) {
                System.out.println("Player hit");

                System.out.println("After Decision:");
                player.AddCard(cardController.SendCard());
                ShowCards();
            }
            System.out.println("Player stand");

            System.out.println("After Decision:");
            ShowCards();


            System.out.println("---dealer round---");
            while (dealer.MakeDecision(dealer.GetVisibleCard()) == Player.DecisionType.HIT) {
                System.out.println("Dealer hit");

                System.out.println("After Decision:");
                dealer.AddCard(cardController.SendCard());
                ShowCards();
            }
            System.out.println("Dealer stand");

            System.out.println("After Decision:");
            ShowCards();

            DisplayResult(player, dealer);

            if (player instanceof HumanPlayer) {
                System.out.println("Do you want to play again? (y/n)");
                String answer = "y";
                if(scanner.hasNext()) {
                    answer=scanner.next();
                }
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
    }

    public void DisplayResult(@NotNull Player player,@NotNull Player dealer) {
        System.out.println("---display result---");
        gameState = GameState.GameOver;
        ShowCards();

        ResultType result;
        if(player.HasWhiteCard()){
            result = ResultType.Lose;
        }else if(dealer.HasWhiteCard()) {
            result = ResultType.Win;
        }else if (player.CalculatePossiblePointSum().size() == 0) {
            result=ResultType.Lose;
        }else if (dealer.CalculatePossiblePointSum().size() == 0) {
            result=ResultType.Win;
        }else{
            int playerCardsSum = player.GetMaxPointSum();
            int dealerCardsSum = dealer.GetMaxPointSum();
            if (playerCardsSum > dealerCardsSum) {
                result=ResultType.Win;
            } else if (playerCardsSum < dealerCardsSum) {
                result=ResultType.Lose;
            } else {
                result=ResultType.Draw;
            }
        }

        switch(result){
            case Win:
                System.out.println("The winner is Player!");
                playerWinCounter++;
                break;
            case Lose:
                System.out.println("The winner is Dealer!");
                dealerWinCounter++;
                break;
            case Draw:
                System.out.println("Draw!There is no winner!");
                drawCounter++;
                break;
        }

    }
    
    private final Player player;
    private final Dealer dealer;
    private final CardController cardController;
    private int playerWinCounter;
    private int dealerWinCounter;
    private int drawCounter;
    private GameState gameState;
}
