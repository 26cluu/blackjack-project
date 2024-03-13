import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        boolean game_over = false;
        Scanner sc = new Scanner(System.in);
        boolean player_loop = true;

        ArrayList<Card> player_hand = new ArrayList<Card>();
        ArrayList<Card> dealer_hand = new ArrayList<Card>();

        while (!game_over) {
            System.out.println("Player:");
            deck.draw(player_hand);
            deck.draw(player_hand);
            showHand(player_hand);
            System.out.println("hand value: " + handValue(player_hand));

            System.out.println("\nDealer:");
            deck.draw(dealer_hand);
            showHand(dealer_hand);
            System.out.println("hidden card");
            System.out.println("hand value: " + handValue(dealer_hand));

            
            while (player_loop == true) {
                System.out.println("Would you like to hit or stay");
                String response = sc.nextLine();
                System.out.println();

                if (response.equals("hit")) {
                    deck.draw(player_hand);
                    System.out.println("player: ");
                    showHand(player_hand);
                    System.out.println(handValue(player_hand));

                    if (bust_check(player_hand)) {
                        System.out.println("you went over 21, you lose");
                        game_over = true;
                        break;
                    }
                }

                if (response.equals("stay")) {
                    player_loop = false;
                }
            }

            dealerDraw(deck, dealer_hand);

            game_over = true;

        }

        String res = win_check(player_hand, dealer_hand);
        System.out.println("Final Hands:");
        System.out.println("\n Dealer: ");
        showHand(dealer_hand);
        System.out.println("Hand value: " + handValue(dealer_hand));

        System.out.println("\n Player:");
        showHand(player_hand);
        System.out.println("Hand value: " + handValue(player_hand));

        switch(res) {
            case "dealer":
                System.out.println("Sorry, the dealer wins this time");
                break;
            case "player":
                System.out.println("Congrats, you won!");
                break;
            case "push":
                System.out.println("You tied with the dealer!");
                break;
        }

        sc.close();
    }

    public static void showHand(ArrayList<Card> hand) {
        for (Card card : hand) {
            System.out.println(card.getValue() + "(" + card.getSuite() + ")");
        }
    }

    public static int handValue(ArrayList<Card> hand) {
        int total = 0;
        for (Card card : hand) {
            total += card.getNumberValue();
        }
        return total;
    }

    public static void dealerDraw(Deck deck, ArrayList<Card> hand) {
        showHand(hand);
        while (handValue(hand) < 17) {
            System.out.println();
            deck.draw(hand);
            showHand(hand);
        }

        System.out.println();
        System.out.println("final hand: ");
        showHand(hand);
    }

    public static boolean bust_check(ArrayList<Card> hand) {
        int total = handValue(hand);
        if (total > 21) {
            return true;
        }
        return false;
    }

    public static String win_check(ArrayList<Card> player, ArrayList<Card> dealer) {
        if (bust_check(player)) {
            System.out.println("player busted");
            return "dealer";
        }
        if (bust_check(dealer)) {
            System.out.println("dealer busted");
            return "player";
        }


        if (handValue(player) > handValue(dealer)) {
            return "player";
        }
        if (handValue(dealer) > handValue(player)) {
            return "dealer";
        }

        if (handValue(dealer) == handValue(player)) {
            return "push";
        }

        return "";
    }

}
