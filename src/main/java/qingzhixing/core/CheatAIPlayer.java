package qingzhixing.core;

import org.jetbrains.annotations.NotNull;

public class CheatAIPlayer extends AIPlayer {
    private int advantagesValue;
    private boolean isEnableCheatAI;

    CheatAIPlayer() {
        advantagesValue = 0;
        SetCardControllerListener(
                new CardControllerListener(
                        (Card send) -> {
                            if (send.GetPoint().intValue <= 6) {
                                advantagesValue++;
                            } else if (send.GetPoint().intValue >= 10) {
                                advantagesValue--;
                            }
                        },
                        () -> advantagesValue = 0
                )
        );
        GameControllerListener gameControllerListener = new GameControllerListener();
        gameControllerListener.SetNewLoopHook(this::NewLoop);
        SetGameControllerListener(gameControllerListener);
        isEnableCheatAI = false;
    }

    private double CalculateAdvantageRate() {
        return (double) (advantagesValue - 1) * 0.5 / 100.0;
    }

    private void NewLoop() {
        //提高门槛以提高胜率
        isEnableCheatAI = CalculateAdvantageRate() > 0.05;
    }

    public boolean IsEnableCheatAI() {
        return isEnableCheatAI;
    }

    @Override
    public void Win() {
        if (isEnableCheatAI) super.Win();
    }

    @Override
    public void Lose() {
        if (isEnableCheatAI) super.Lose();
    }

    @Override
    public void Draw() {
        if (isEnableCheatAI) super.Draw();
    }

    @Override
    public int GetConsumedLoopAmount() {
        if (isEnableCheatAI) return super.GetConsumedLoopAmount();
        return 0;
    }

    @Override
    public @NotNull Player.DecisionType MakeDecision(@NotNull Card dealerVisibleCard) {
        if (isEnableCheatAI) {
            System.out.println("Cheat AI deciding...");
        } else {
            System.out.println("Normal AI deciding...");
        }
        System.out.printf("The advantage rate is:%.2f%%\n", CalculateAdvantageRate() * 100);
        return super.MakeDecision(dealerVisibleCard);
    }
}
