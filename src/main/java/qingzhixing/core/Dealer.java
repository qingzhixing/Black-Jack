package qingzhixing.core;

import org.jetbrains.annotations.NotNull;

public class Dealer extends Player {

    @Override
    public @NotNull DecisionType MakeDecision(@NotNull Card dealerVisibleCard) {
        if (this.CheckBust()) {
            return DecisionType.STAND;
        }
        int maxSum = this.GetMaxPointSum();
        if (maxSum >= 17) {
            System.out.println("Dealer's max sum is " + maxSum + " and dealer will stand.");
            return DecisionType.STAND;
        }
        return DecisionType.HIT;
    }

    public String GetCardsStringHideFirst() {
        if (cards.size() == 0) {
            return "[ ]";
        }

        StringBuilder stringBuilder = new StringBuilder("#");
        if (cards.size() >= 2) {
            stringBuilder.append(", ");
        }
        for (int i = 1; i < cards.size(); i++) {
            stringBuilder.append(cards.get(i).GetPoint().toString());
            if (i != cards.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return "[ " + stringBuilder + " ]";
    }

    public Card GetVisibleCard() {
        if (cards.size() < 2) {
            return null;
        }
        return cards.get(1);
    }
}
