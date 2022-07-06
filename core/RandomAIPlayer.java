package core;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RandomAIPlayer extends AIPlayer {
    @Override
    public @NotNull DecisionType MakeDecision(@NotNull Card dealerVisibleCard) {

        if (this.CheckBust()) {
            System.out.println("Random AI is already busted");
            return DecisionType.STAND;
        }
        if (this.GetMaxPointSum() == 21) {
            System.out.println("Random AI is already Black Jack!");
            return DecisionType.STAND;
        }
        return new Random().nextBoolean() ? DecisionType.HIT : DecisionType.STAND;
    }
}
