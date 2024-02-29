import java.util.*;

public class Player {
    ArrayList<Card> hand = new ArrayList<Card>();




    public void draw(Card card) {
        hand.add(card);
    }
}
