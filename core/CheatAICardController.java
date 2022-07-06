package core;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CheatAICardController extends CardController {
    private final CheatAIChecker checker;

    public CheatAICardController(int deckAmount, boolean enableWhiteCard, CheatAIChecker checker) {
        super(deckAmount, enableWhiteCard);
        this.checker = checker;
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

            //普通AI模式下大概率发小牌
            while (!checker.IsEnableCheatAI() && send.GetPoint().intValue >= 10 && (new Random().nextInt(100) <= 80)) {
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

    @FunctionalInterface
    public interface CheatAIChecker {
        boolean IsEnableCheatAI();
    }
}
