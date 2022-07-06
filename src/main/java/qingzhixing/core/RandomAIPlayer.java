package qingzhixing.core;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RandomAIPlayer extends AIPlayer {
    @Override
    public @NotNull Player.DecisionType MakeDecision(@NotNull Card dealerVisibleCard) {

        if (this.CheckBust()) {
            System.out.println("Random AI is already busted");
            return Player.DecisionType.STAND;
        }
        if (this.GetMaxPointSum() == 21) {
            System.out.println("Random AI is already Black Jack!");
            return Player.DecisionType.STAND;
        }
        return new Random().nextBoolean() ? Player.DecisionType.HIT : Player.DecisionType.STAND;
    }
}
