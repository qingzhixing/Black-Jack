package core;

public class PlayerFactory {
    public static enum GameMode {
        HUMAN, AI, CHEAT_AI;
    }

    public static Player NewPlayer(GameMode mode) {
        switch (mode) {
            case HUMAN:
                return new PlayerHuman();
            case AI:
                return new PlayerAI();
            case CHEAT_AI:
                return new PlayerCheatAI();
            default:
                return null;
        }
    }
}
