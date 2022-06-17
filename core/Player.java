package core;

import java.util.ArrayList;

public class Player {
    public Player() {
        cards = new ArrayList<>();
        isBlackJack=false;
    }
    
    public void DrawInCard(Card card) {
        cards.add(card);
    }

    public boolean IsBlackJack() {
        return isBlackJack;
    }

    public void SetBlackJack(boolean isBlackJack) {
        this.isBlackJack = isBlackJack;
    }

    public boolean CheckBust() {
        int pointCounter = 0;
        for (Card card : cards) {
            pointCounter += card.getPoint().intValue;
        }
        return pointCounter > 21;
    }

    private boolean isBlackJack;
    private ArrayList<Card> cards;
}
