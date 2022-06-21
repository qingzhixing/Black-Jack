import java.util.Scanner;

import core.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gameController;
        System.out.println("Welcome to BlackJack!");
        System.out.println("How many decks do you want to use?");
        int deckAmount = scanner.nextInt();
        System.out.println("Do you want to enable white card? (y/n)");
        boolean enableWhiteCard = scanner.next().equals("y");

        System.out.println("Choose Game Mode: [1] Human. [2] AI. [3] AI with Cheat [0] Quit");
        int gameMode = scanner.nextInt();
        Player playerAI;
        switch (gameMode) {
            case 1:
                playerAI = PlayerFactory.CreatePlayer(PlayerFactory.PlayerType.HUMAN);
                break;
            case 2:
                playerAI = PlayerFactory.CreatePlayer(PlayerFactory.PlayerType.AI);
                break;
            case 3:
                playerAI = PlayerFactory.CreatePlayer(PlayerFactory.PlayerType.CHEAT_AI);
                break;
            case 0:
                System.out.println("Thanks for playing!");
                scanner.close();
                return;
            default:
                System.out.println("Invalid input!");
                scanner.close();
                return;
        }
        gameController = new GameController(deckAmount, enableWhiteCard,playerAI);
        gameController.StartGame();
        scanner.close();
    }
}
