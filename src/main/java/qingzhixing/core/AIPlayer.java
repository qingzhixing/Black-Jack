package qingzhixing.core;

import org.jetbrains.annotations.NotNull;

public class AIPlayer extends Player {

    @Override
    public Player.@NotNull DecisionType MakeDecision(@NotNull Card dealerVisibleCard) {
        if (this.CheckBust()) {
            System.out.println("AI busted");
            return Player.DecisionType.STAND;
        }
        int maxSum = this.GetMaxPointSum();
        if (maxSum == 21) {
            System.out.println("AI Black Jack!");
            return Player.DecisionType.STAND;
        }
        if (maxSum >= 17) {
            System.out.println("AI decided to stand");
            return DecisionType.STAND;
        }
        if (maxSum <= 11) {
            System.out.println("AI decided to hit");
            return DecisionType.HIT;
        }
        if (dealerVisibleCard.GetPoint().intValue >= 7) {
            System.out.println("AI decided to hit");
            return DecisionType.HIT;
        } else {
            System.out.println("AI decided to stand");
            return DecisionType.STAND;
        }
    }

}
