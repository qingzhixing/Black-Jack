package core;

public class PlayerFactory {
    public static enum GameMode {
        HUMAN, AI, CHEAT_AI;
    }

    public static Player NewPlayer(GameMode mode) {
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
}
