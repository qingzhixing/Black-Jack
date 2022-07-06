package qingzhixing.core;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class Player {
    protected ArrayList<Card> cards;
    private int winCounter;
    private int loseCounter;
    private int drawCounter;
    private CardControllerListener cardControllerListener;
    private GameControllerListener gameControllerListener;

    public Player() {
        cards = new ArrayList<>();
        winCounter = 0;
        loseCounter = 0;
        drawCounter = 0;
        cardControllerListener = new CardControllerListener();
        gameControllerListener = new GameControllerListener();
    }

    public final void AddCard(@NotNull Card card) {
        cards.add(card);
    }

    public final @NotNull ArrayList<Card> GetCards() {
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

    public abstract @NotNull DecisionType MakeDecision(@NotNull Card dealerVisibleCard);

    public final boolean CheckBust() {
        return CalculatePossiblePointSum().size() == 0;
    }

    public @NotNull String GetCardsString() {
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

    public final int GetMaxPointSum() {
        int sum = 0;
        ArrayList<Integer> possiblePointSum = this.CalculatePossiblePointSum();
        for (var item : possiblePointSum) {
            sum = Math.max(sum, item);
        }
        return sum;
    }

    public void Win() {
        winCounter++;
    }

    public void Lose() {
        loseCounter++;
    }

    public void Draw() {
        drawCounter++;
    }

    public final double CalculateWinningProbability() {
        if (winCounter == 0) {
            return 0;
        }
        if (loseCounter == 0) {
            return 1;
        }
        return (double) winCounter / (winCounter + loseCounter);
    }

    public int GetConsumedLoopAmount() {
        return 1;
    }

    public final int GetWinCounter() {
        return winCounter;
    }

    public final int GetLoseCounter() {
        return loseCounter;
    }

    public final int GetDrawCounter() {
        return drawCounter;
    }

    public final void SetCardControllerListener(@NotNull CardControllerListener listener) {
        this.cardControllerListener = listener;
    }

    public final @NotNull CardControllerListener GetCardControllerListener() {
        return cardControllerListener;
    }

    public final void SetGameControllerListener(@NotNull GameControllerListener listener) {
        this.gameControllerListener = listener;
    }

    public final @NotNull GameControllerListener GetGameControllerListener() {
        return gameControllerListener;
    }

    public enum DecisionType {
        HIT,
        STAND,
    }
}
