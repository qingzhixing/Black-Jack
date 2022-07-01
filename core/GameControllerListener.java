package core;

import org.jetbrains.annotations.NotNull;

public class GameControllerListener {
    private GameStartHook gameStartHook;
    private GameEndHook gameEndHook;
    private GameDrawHook gameDrawHook;
    private GameWinHook gameWinHook;
    private GameLoseHook gameLoseHook;
    private NewLoopHook newLoopHook;

    public GameControllerListener() {
        this.gameStartHook = new GameStartHook() {
            @Override
            public void OnGameStart() {
            }
        };
        this.gameEndHook = new GameEndHook() {
            @Override
            public void OnGameEnd() {
            }
        };
        this.gameDrawHook = new GameDrawHook() {
            @Override
            public void OnGameDraw() {
            }
        };
        this.gameWinHook = new GameWinHook() {
            @Override
            public void OnGameWin() {
            }
        };
        this.gameLoseHook = new GameLoseHook() {
            @Override
            public void OnGameLose() {
            }
        };
        this.newLoopHook = new NewLoopHook() {
            @Override
            public void OnNewLoop() {
            }
        };
    }

    public GameControllerListener(
            @NotNull GameStartHook gameStartHook,
            @NotNull GameEndHook gameEndHook,
            @NotNull GameDrawHook gameDrawHook,
            @NotNull GameWinHook gameWinHook,
            @NotNull GameLoseHook gameLoseHook,
            @NotNull NewLoopHook newLoopHook
    ) {
        this.gameStartHook = gameStartHook;
        this.gameEndHook = gameEndHook;
        this.gameDrawHook = gameDrawHook;
        this.gameWinHook = gameWinHook;
        this.gameLoseHook = gameLoseHook;
        this.newLoopHook = newLoopHook;
    }

    public void SetGameStartHook(@NotNull GameStartHook gameStartHook) {
        this.gameStartHook = gameStartHook;
    }

    public void SetGameEndHook(@NotNull GameEndHook gameEndHook) {
        this.gameEndHook = gameEndHook;
    }

    public void SetGameDrawHook(@NotNull GameDrawHook gameDrawHook) {
        this.gameDrawHook = gameDrawHook;
    }

    public void SetGameWinHook(@NotNull GameWinHook gameWinHook) {
        this.gameWinHook = gameWinHook;
    }

    public void SetGameLoseHook(@NotNull GameLoseHook gameLoseHook) {
        this.gameLoseHook = gameLoseHook;
    }

    public void SetNewLoopHook(@NotNull NewLoopHook newLoopHook) {
        this.newLoopHook = newLoopHook;
    }

    public void OnGameStart() {
        gameStartHook.OnGameStart();
    }

    public void OnGameEnd() {
        gameEndHook.OnGameEnd();
    }

    public void OnGameDraw() {
        gameDrawHook.OnGameDraw();
    }

    public void OnGameWin() {
        gameWinHook.OnGameWin();
    }

    public void OnGameLose() {
        gameLoseHook.OnGameLose();
    }

    public void OnNewLoop() {
        newLoopHook.OnNewLoop();
    }

    @FunctionalInterface
    public interface GameStartHook {
        void OnGameStart();
    }

    @FunctionalInterface
    public interface GameEndHook {
        void OnGameEnd();
    }

    @FunctionalInterface
    public interface GameDrawHook {
        void OnGameDraw();
    }

    @FunctionalInterface
    public interface GameWinHook {
        void OnGameWin();
    }

    @FunctionalInterface
    public interface GameLoseHook {
        void OnGameLose();
    }

    @FunctionalInterface
    public interface NewLoopHook {
        void OnNewLoop();
    }

}
