package core;

import java.util.LinkedList;
import java.util.Random;

public class CardController {
    public CardController() {
        SetDeckAmount(1);
        enableWhiteCard = false;
        cards = new LinkedList<>();
        InitializeCards();
    }

    public CardController(int deckAmount,boolean enableWhiteCard) throws IllegalArgumentException {
        SetDeckAmount(deckAmount);
        this.enableWhiteCard = enableWhiteCard;
        cards = new LinkedList<>();
        InitializeCards();
    }

    public void InitializeCards() {
        cards.clear();
        for (int deck = 1; deck <= deckAmount; deck++) {
            for (int suit = 1; suit <= 4; suit++) {
                for (Card.CardPoints point : Card.CardPoints.values()) {
                    if (point == Card.CardPoints.CardWhite) {
                        continue;
                    }
                    cards.add(new Card(point));
                }
            }
        }
        if (enableWhiteCard) {
            cards.add(new Card(Card.CardPoints.CardWhite));
        }
    }
    
    public Card SendCard() {
        if (cards.size() < 10) {
            //reinitialize cards
            InitializeCards();
        }
        Random rand=new Random();
        int randomIndex = Math.abs(rand.nextInt()) % cards.size();
        return cards.remove(randomIndex);
    }

    public int GetDeckAmount() {
        return deckAmount;
    }

    public void SetDeckAmount(int amount) throws IllegalArgumentException {
        if (amount < 1 || amount > 8) {
            throw new IllegalArgumentException("Deck amount must be greater than 0 and less than 9.");
        }
        this.deckAmount = amount;
    }

    public boolean IsEnableWhiteCard() {
        return enableWhiteCard;
    }

    public void SetEnableWhiteCard(boolean enableWhiteCard) {
        this.enableWhiteCard = enableWhiteCard;
    }

    private int deckAmount;
    private final LinkedList<Card> cards;
    private boolean enableWhiteCard;

}