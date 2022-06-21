package core;

public class Card {
    public static enum CardPoints {
        Card2(2, "2"),
        Card3(3, "3"),
        Card4(4, "4"),
        Card5(5, "5"),
        Card6(6, "6"),
        Card7(7, "7"),
        Card8(8, "8"),
        Card9(9, "9"),
        Card10(10, "10"),
        CardJ(10, "J"),
        CardQ(10, "Q"),
        CardK(10, "K"),
        CardA(11, "A"),
        CardWhite(0, "White");

        public final int intValue;
        public final String stringValue;

        CardPoints(int intValue, String stringValue) {
            this.intValue = intValue;
            this.stringValue = stringValue;
        }

        public String toString() {
            return stringValue;
        }
    }

    public Card() {
        point = CardPoints.CardA;
    }

    public Card(CardPoints point) {
        this.point = point;
    }

    

    private CardPoints point;

    public CardPoints GetPoint() {
        return point;
    }
}
