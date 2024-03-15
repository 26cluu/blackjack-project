import java.util.*;


public class Deck {
    Random rand = new Random();

    //to be changed to symbols later on
    String[] suites = {"diamonds", "hearts", "clubs", "spades"};
    //numbers and faces
    String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    ArrayList<Card> deck = new ArrayList<Card>();

    //creates entire deck
    public Deck() {
        for (String suite: suites) {
            for (String value : values) {
                Card current = new Card(value, suite);
                deck.add(current);
            }
        }
    }

    //draws a random card from the deck
    public void draw(ArrayList<Card> hand) {
        int deckNumber = rand.nextInt(51);
        hand.add(deck.get(deckNumber));
        deck.remove(deckNumber);
    }

}
