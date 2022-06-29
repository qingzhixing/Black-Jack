package core;

public class PlayerFactory {
    public static Player CreatePlayer(PlayerType mode) {
        switch (mode) {
            case HUMAN:
                return new HumanPlayer();
            case AI:
                return new AIPlayer();
            case CHEAT_AI:
                return new CheatAIPlayer();
            default:
                return null;
        }
    }

    public enum PlayerType {
        HUMAN, AI, CHEAT_AI;
    }
}