package core;

import java.util.ArrayList;

public abstract class Player {
    // public static final enum 

    public Player() {
        cards = new ArrayList<>();
    }
    
    public void DrawInCard(Card card) {
        cards.add(card);
    }

    public boolean IsBlackJack() {
        if (cards.size() != 2) {
            return false;
        }
        return (cards.get(0).getPoint() == Card.CardPoints.CardA) &&
        (cards.get(1).getPoint().intValue == 10);
    }

    public boolean CheckBust() {
        int pointCounter = 0;
        for (Card card : cards) {
            pointCounter += card.getPoint().intValue;
        }
        return pointCounter > 21;
    }

    public abstract int Decision();

    public ArrayList<Card> cards;
}
