package core;

public class GameController {
    public GameController(int deckAmount, boolean enableWhiteCard) {
        cardController=new CardController(deckAmount,enableWhiteCard);
    }
    private CardController cardController;
}
