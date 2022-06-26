package core;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class Player {
    public enum DecisionType {
        HIT,
        STAND,
    }

    public Player() {
        cards = new ArrayList<>();
    }
    
    public final void AddCard(Card card) {
        cards.add(card);
    }

    public final ArrayList<Card> GetCards() {
        return cards;
    }

    public final @NotNull ArrayList<Integer> CalculatePossiblePointSum() {
        ArrayList<Integer> possiblePointSum = new ArrayList<>();
        
        int cardACounter = 0;
        int pointSum = 0;
        for (Card card : cards) {
            pointSum += card.GetPoint().intValue;
            if (card.GetPoint() == Card.CardPoints.CardA) {
                cardACounter++;
            }
        }
        while (cardACounter >= 0) {
            int processedSum = pointSum - cardACounter * 10;
            if (processedSum <= 21 && processedSum >= 0) {
                possiblePointSum.add(processedSum);
            }
            cardACounter--;
        }

        return possiblePointSum;
    }

    public abstract DecisionType MakeDecision(Card dealerVisibleCard);

    public final boolean CheckBust(){
        return CalculatePossiblePointSum().size() == 0;
    }

    public String GetCardsString() {
        if (cards.size() == 0) {
            return "[ ]";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(cards.get(i).GetPoint().toString());
            if (i != cards.size() - 1) {
                sb.append(", ");
            }
        }
        return "[ " + sb + " ]";
    }

    public final int GetMaxPointSum(){
        int sum=0;
        ArrayList<Integer> possiblePointSum = this.CalculatePossiblePointSum();
        for(var item:possiblePointSum){
            sum=Math.max(sum,item);
        }
        return sum;
    }

    protected ArrayList<Card> cards;
}
