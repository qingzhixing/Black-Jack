package core;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CheatAICardController extends CardController {
    public CheatAICardController(int deckAmount, boolean enableWhiteCard) {
        super(deckAmount, enableWhiteCard);
    }

    //大概率发小牌
    @Override
    public @NotNull Card SendCard() {
        if (cards.size() < 10) {
            //reinitialize cards
            InitializeCards();
        }
        Card send;
        do {
            int randomIndex = new Random().nextInt(cards.size());
            send = cards.get(randomIndex);

            //拿到小牌大概率重新洗牌
            while (send.GetPoint().intValue <= 6 && (new Random().nextInt(100) <= 70)) {
                randomIndex = new Random().nextInt(cards.size());
                send = cards.get(randomIndex);
            }

            if (send.GetPoint() == Card.CardPoints.CardWhite) {
                System.out.println("Next card is Card White,reinitialize cards.");
                InitializeCards();
            }
        } while (send.GetPoint() == Card.CardPoints.CardWhite);
        cards.remove(send);
        GetListener().OnCardSend(send);
        return send;
    }
}
