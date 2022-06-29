package core;

public class AIPlayer extends Player {

    @Override
    public Player.DecisionType MakeDecision(Card dealerVisibleCard) {
        if (this.CheckBust()) {
            System.out.println("AI busted");
            return DecisionType.STAND;
        }
        int maxSum = this.GetMaxPointSum();
        if (maxSum == 21) {
            System.out.println("AI Black Jack!");
            return DecisionType.STAND;
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
