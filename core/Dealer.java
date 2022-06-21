package core;

public class Dealer extends Player{

    @Override
    public int MakeDecision(Card dealerVisibleCard) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public String GetCardsStringHideFirst() {
        if (cards.size() == 0) {
            return "[ ]";
        }

        StringBuilder sb = new StringBuilder("#");
        if (cards.size() >= 2) {
            sb.append(", ");
        }
        for (int i = 1; i < cards.size(); i++) {
            sb.append(cards.get(i).GetPoint().toString());
            if (i != cards.size() - 1) {
                sb.append(", ");
            }
        }
        return "[ " + sb.toString() + " ]";
    }
}
