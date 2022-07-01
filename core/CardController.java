package core;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Random;

public class CardController {
    private final LinkedList<Card> cards;
    private int deckAmount;
    private boolean enableWhiteCard;
    private CardControllerListener listener = new CardControllerListener();

    public CardController() {
        SetDeckAmount(1);
        enableWhiteCard = false;
        cards = new LinkedList<>();
        InitializeCards();
    }

    public CardController(int deckAmount, boolean enableWhiteCard) throws IllegalArgumentException {
        SetDeckAmount(deckAmount);
        this.enableWhiteCard = enableWhiteCard;
        cards = new LinkedList<>();
        InitializeCards();
    }

    public void InitializeCards() {
        listener.OnCardInitialize();
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

    public @NotNull Card SendCard() {
        if (cards.size() < 10) {
            //reinitialize cards
            InitializeCards();
        }
        Card send;
        do {
            int randomIndex = new Random().nextInt(cards.size());
            send = cards.get(randomIndex);
            if (send.GetPoint() == Card.CardPoints.CardWhite) {
                System.out.println("Next card is Card White,reinitialize cards.");
                InitializeCards();
            }
        } while (send.GetPoint() == Card.CardPoints.CardWhite);
        cards.remove(send);
        listener.OnCardSend(send);
        return send;
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

    public void SetListener(@NotNull CardControllerListener listener) {
        this.listener = listener;
    }

}