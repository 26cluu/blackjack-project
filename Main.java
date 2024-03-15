//by Cody Luu and Blake Almon

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //decks and trackers
        Deck deck = new Deck();
        boolean game_over = false;
        Scanner sc = new Scanner(System.in);
        boolean player_loop = true;


        //hands
        ArrayList<Card> player_hand = new ArrayList<Card>();
        ArrayList<Card> dealer_hand = new ArrayList<Card>();

        //player turn
        while (!game_over) {
            //starting phase with card reveals
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

            //hit or stay phase for player
            while (player_loop == true) {
                String hitOrStayMessage = "Would you like to hit or stay";
                printWithDelay(hitOrStayMessage);

                String response = sc.nextLine();

                System.out.println();

                if (response.equals("hit")) {
                    clearScreen();
                    
                    //checks for bust
                    if (bust_check(player_hand)) {
                        clearScreen();
                        String bustMessage1 = "you went over 21, you lose";
                        printWithDelay(bustMessage1);
                        
                        Thread.sleep(200);
                        printWithDelay(firstDealerMessage3);

                        game_over = true;
                        break;
                    }

                    //draws card
                    deck.draw(player_hand);

                    //shows hit messages
                    String hitMessage1 = "player: ";
                    printWithDelay(hitMessage1);

                    showHand(player_hand);

                    
                    String hitMessage2 = String.valueOf((handValue(player_hand)));
                    printWithDelay(hitMessage2);

    

                    printWithDelay(firstDealerMessage1);
                    printWithDelay(firstDealerMessage2);
                    printWithDelay(firstDealerMessage3);

                }

                //ends hit phase once player chooses to stay
                if (response.equals("stay")) {
                    player_loop = false;
                }
            }
            clearScreen();

            //dealer turn initiates
            dealerDraw(deck, dealer_hand);


            //ends player turn
            game_over = true;

        }
        clearScreen();

        Thread.sleep(150);


        //checks who won
        String res = win_check(player_hand, dealer_hand);

        //shows final hands and values
        System.out.println("Final Hands:");
        System.out.println("\n Dealer: ");
        showHand(dealer_hand);
        System.out.println("Hand value: " + handValue(dealer_hand));

        System.out.println("\n Player:");
        showHand(player_hand);
        System.out.println("Hand value: " + handValue(player_hand));

        //different statements depending on who won
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

    //method to print out cards in given hand
    public static void showHand(ArrayList<Card> hand) {
        for (Card card : hand) {
            System.out.println(card.getValue() + " (" + card.getSuite() + ")");
        }
    }


    //calculates the value of a hand
    public static int handValue(ArrayList<Card> hand) {
        //tracks aces in hand and total hand value
        int total = 0;
        int ace = 0;
        for (Card card : hand) {
            total += card.getNumberValue();
            if (card.getValue().equals("A")) {
                ace++;
            }
        }
        //uses ace as 11 default but changes it to 1 if the hand value goes over 21
        //while loop incase hand needs to change multiple aces
        while ((total > 21) && (ace > 0)) {
            total -= 10;
            ace --;
        }

        return total;
    }

    //draws for dealer until < 17 or busts
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

    //checks if a hand is over 21
    public static boolean bust_check(ArrayList<Card> hand) {
        int total = handValue(hand);
        if (total > 21) {
            return true;
        }
        return false;
    }

    //returns who won
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

    //clears the screen
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
