package core;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class HumanPlayer extends Player {
    @Override
    public @NotNull DecisionType MakeDecision(@NotNull Card dealerVisibleCard) {

        System.out.println("Human Deciding...");
        if (this.CheckBust()) {
            System.out.println("You're already busted");
            return DecisionType.STAND;
        }
        if (this.GetMaxPointSum() == 21) {
            System.out.println("You're already Black Jack!");
            return DecisionType.STAND;
        }

        Scanner scanner = new Scanner(System.in);
        DecisionType decision = DecisionType.STAND;
        boolean inputValid = false;
        while (!inputValid) {
            System.out.printf("The Dealer's cards are [ #, %s ]\n", dealerVisibleCard.GetPoint().toString());
            System.out.println("Please decide:[1] Hit. [2] Stand.");
            int input = 1;
            if (scanner.hasNext()) {
                input = scanner.nextInt();
            }
            inputValid = true;
            switch (input) {
                case 1:
                    decision = DecisionType.HIT;
                    break;
                case 2:
                    decision = DecisionType.STAND;
                    break;
                default:
                    inputValid = false;
                    break;
            }
        }
        return decision;
    }
}
