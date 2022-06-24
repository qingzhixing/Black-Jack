import java.util.Scanner;

import core.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gameController;
        System.out.println("Welcome to BlackJack!");
        System.out.println("How many decks do you want to use? (1 to 8)");
        int deckAmount = 0;
        if(scanner.hasNext()){
            deckAmount = scanner.nextInt();
        }
        if(deckAmount<=0||deckAmount>=9){
            System.out.println("Deck amount must be greater than 0 and less than 9.Set to 1.");
        }
        System.out.println("Do you want to enable white card? (y/n)");
        boolean enableWhiteCard = false;
        if(scanner.hasNext()){
            enableWhiteCard=scanner.next().equals("y");
        }

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
                return;
        default :
                System.out.println("Invalid input!");
                return;
        }
        gameController = new GameController(deckAmount, enableWhiteCard,playerAI);
        gameController.StartGame();
    }
}
