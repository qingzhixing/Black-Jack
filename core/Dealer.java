package core;

public class Dealer extends Player{

    @Override
    public DecisionType MakeDecision(Card dealerVisibleCard) {
        if (this.CheckBust()) {
            return DecisionType.STAND;
        }
        int maxSum=0;
        for (int sum : this.CalculatePossiblePointSum()) {
            sum = Math.max(sum, maxSum);
        }
        if (maxSum >= 17) {
            return DecisionType.STAND;
        }
        return DecisionType.HIT;
    }
    
    public String GetCardsStringHideFirst() {
        if (cards.size() == 0) {
            return "[ ]";
        }

        StringBuilder sb = new StringBuilder("#");
        if (cards.size() >= 2) {
            sb.append(", ");
        }
        for (int i = 1; i < cards.size(); i++) {
            sb.append(cards.get(i).GetPoint().toString());
            if (i != cards.size() - 1) {
                sb.append(", ");
            }
        }
        return "[ " + sb.toString() + " ]";
    }

    public Card GetVisibleCard() {
        if (cards.size() < 2) {
            return null;
        }
        return cards.get(1);
    }
}
