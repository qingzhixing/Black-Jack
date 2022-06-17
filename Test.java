import core.*;

public class Test {
    public static void main(String[] args) {
        CardController cc = new CardController(8, true);
        Card card;
        while ((card = cc.DrawCard()) != null) {
            System.out.printf("%s ",card.getPoint());
        }
    }
}
