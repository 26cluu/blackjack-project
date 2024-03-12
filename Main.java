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
                }

                if (response.equals("stay")) {
                    player_loop = false;
                }
            }

            dealerDraw(deck, dealer_hand);

            game_over = true;

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

}
