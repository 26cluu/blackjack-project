import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Deck deck = new Deck();
        boolean game_over = false;
        Scanner sc = new Scanner(System.in);
        boolean player_loop = true;

        ArrayList<Card> player_hand = new ArrayList<Card>();
        ArrayList<Card> dealer_hand = new ArrayList<Card>();

        while (!game_over) {
            String playerMessage1 = "Player:";
            printWithDelay(playerMessage1);

            deck.draw(player_hand);
            deck.draw(player_hand);
            showHand(player_hand);

            String playerMessage2 = "hand value: " + handValue(player_hand);
            printWithDelay(playerMessage2);

            String firstDealerMessage1 = "\nDealer:";
            printWithDelay(firstDealerMessage1);
            
            deck.draw(dealer_hand);
            showHand(dealer_hand);

            String firstDealerMessage2 = "hidden card";
            printWithDelay(firstDealerMessage2);

            String firstDealerMessage3 = "hand value: " + handValue(dealer_hand);
            printWithDelay(firstDealerMessage3);

            
            while (player_loop == true) {
                String hitOrStayMessage = "Would you like to hit or stay";
                printWithDelay(hitOrStayMessage);

                String response = sc.nextLine();

                System.out.println();

                if (response.equals("hit")) {
                    clearScreen();
                    

                    if (bust_check(player_hand)) {
                        clearScreen();
                        String bustMessage1 = "you went over 21, you lose";
                        printWithDelay(bustMessage1);
                        
                        Thread.sleep(200);
                        printWithDelay(firstDealerMessage3);

                        game_over = true;
                        break;
                    }

                    deck.draw(player_hand);

                    String hitMessage1 = "player: ";
                    printWithDelay(hitMessage1);

                    showHand(player_hand);

                    
                    String hitMessage2 = String.valueOf((handValue(player_hand)));
                    printWithDelay(hitMessage2);

    

                    printWithDelay(firstDealerMessage1);
                    printWithDelay(firstDealerMessage2);
                    printWithDelay(firstDealerMessage3);

                }

                if (response.equals("stay")) {
                    player_loop = false;
                }
            }
            clearScreen();

            dealerDraw(deck, dealer_hand);

            game_over = true;

        }
        clearScreen();

        Thread.sleep(150);

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

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  


    //print method for typing effect in console
    public static void printWithDelay(String x) throws InterruptedException{
        for (int count=0; count < x.length(); count++){
            System.out.print(x.charAt(count));
            TimeUnit.MILLISECONDS.sleep(40);
        }
        System.out.println();
    }

}
