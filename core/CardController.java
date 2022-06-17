package core;

import java.util.ArrayList;

public class CardController {
    public static ArrayList<Card> GenerateCardSet() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card.CardPoints point : Card.CardPoints.values()) {
            if (point == Card.CardPoints.CardWhite) {
                continue;
            }
            cards.add(new Card(point));
        }
        return cards;
    }

    public static ArrayList<Card> ShuffleCards(ArrayList<Card> cards) {
        ArrayList<Card> shuffledCards = new ArrayList<>();
        while (cards.size() > 0) {
            int randomIndex = (int) (Math.random() * cards.size());
            shuffledCards.add(cards.get(randomIndex));
            cards.remove(randomIndex);
        }
        return shuffledCards;
    }

    public CardController() {
        setDeckAmount(1);
        this.enableWhiteCard = false;
    }

    public CardController(int deckAmount,boolean enableWhiteCard) throws IllegalArgumentException {
        try{
            setDeckAmount(deckAmount);
            this.enableWhiteCard = enableWhiteCard;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public void InitializeCards() {
        for (int i = 1; i <= deckAmount; i++) {
            cards.addAll(GenerateCardSet());
        }
        if (enableWhiteCard) {
            cards.add(new Card(Card.CardPoints.CardWhite));
        }
        cards = ShuffleCards(cards);
    }
    
    public Card DrawCard() {
        if (cards.size() > 0) {
            return cards.remove(0);
        } else {
            return null;
        }
    }

    public int getDeckAmount() {
        return deckAmount;
    }

    public void setDeckAmount(int amount) throws IllegalArgumentException {
        if (amount < 1 || amount > 8) {
            throw new IllegalArgumentException("Deck amount must be greater than 0 and less than 9.");
        }
        this.deckAmount = amount;
    }

    public boolean isEnableWhiteCard() {
        return enableWhiteCard;
    }

    public void setEnableWhiteCard(boolean enableWhiteCard) {
        this.enableWhiteCard = enableWhiteCard;
    }

    private int deckAmount;
    private ArrayList<Card> cards;
    private boolean enableWhiteCard;

}