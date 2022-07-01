package core;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class GameController {
    private final Player player;
    private final Dealer dealer;
    private final CardController cardController;
    private GameState gameState;
    private GameControllerListener gameControllerListener = new GameControllerListener();

    public GameController() {
        player = new HumanPlayer();
        dealer = new Dealer();
        cardController = new CardController();
        //设置钩子
        cardController.SetListener(player.GetCardControllerListener());
        gameControllerListener = player.GetGameControllerListener();
    }

    public GameController(int deckAmount, boolean enableWhiteCard, Player playerAI) {
        player = playerAI;
        dealer = new Dealer();
        cardController = new CardController(deckAmount, enableWhiteCard);
        //设置钩子
        cardController.SetListener(player.GetCardControllerListener());
        gameControllerListener = player.GetGameControllerListener();
    }

    public void SetListener(@NotNull GameControllerListener gameControllerListener) {
        this.gameControllerListener = gameControllerListener;
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
        gameControllerListener.OnGameStart();

        cardController.InitializeCards();
        Scanner scanner = new Scanner(System.in);
        int loops = 1;
        if (player instanceof AIPlayer) {
            System.out.println("How many loops do you want AI play?");
            if (scanner.hasNext()) {
                loops = scanner.nextInt();
            }
        }
        boolean breakable = true;
        while (loops > 0 || !breakable) {
            gameControllerListener.OnNewLoop();

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
                if (scanner.hasNext()) {
                    answer = scanner.next();
                }
                if (answer.equals("y")) {
                    breakable = false;
                }
            }
            //不同类型的AI游戏局数不同
            loops -= player.GetConsumedLoopAmount();
        }
        System.out.println("---Game End---");
        System.out.printf("Player won %d times\n", player.GetWinCounter());
        System.out.printf("Player lost %d times\n", player.GetLoseCounter());
        System.out.printf("Drew %d times\n", player.GetDrawCounter());
        System.out.printf("The probability of player winning is %.2f%%\n",
                player.CalculateWinningProbability() * 100);
        gameControllerListener.OnGameEnd();
    }

    public void DisplayResult(@NotNull Player player, @NotNull Player dealer) {
        System.out.println("---display result---");
        gameState = GameState.GameOver;
        ShowCards();

        ResultType result;
        if (player.CheckBust()) {
            result = ResultType.Lose;
        } else if (dealer.CheckBust()) {
            result = ResultType.Win;
        } else {
            int playerCardsSum = player.GetMaxPointSum();
            int dealerCardsSum = dealer.GetMaxPointSum();
            if (playerCardsSum > dealerCardsSum) {
                result = ResultType.Win;
            } else if (playerCardsSum < dealerCardsSum) {
                result = ResultType.Lose;
            } else {
                result = ResultType.Draw;
            }
        }

        switch (result) {
            case Win:
                System.out.println("The winner is Player!");
                player.Win();
                dealer.Lose();
                gameControllerListener.OnGameWin();
                break;
            case Lose:
                System.out.println("The winner is Dealer!");
                dealer.Win();
                player.Lose();
                gameControllerListener.OnGameLose();
                break;
            case Draw:
                System.out.println("Draw!There is no winner!");
                player.Draw();
                dealer.Draw();
                gameControllerListener.OnGameDraw();
                break;
        }

    }

    public enum GameState {
        GameGoing,
        GameOver,
    }

    public enum ResultType {
        Win,
        Lose,
        Draw,
    }
}
