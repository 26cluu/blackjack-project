import java.util.*;
public class Card {
    
    String suite, value;

    //to be changed to symbols later on
    String[] suites = {"diamonds", "cloves", "hearts", "spades"};
    //numbers and faces
    String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public Card(String value, String suite) {
        this.value = value;
        this.suite = suite;
    }

    public String getValue() {
        return this.value;
    }

    public String getSuite() {
        return this.suite;
    }

    public int getNumberValue(Card card) {
        return Arrays.asList(values).indexOf(card.getValue()) + 1;
    }

    public int getSuiteValue(Card card) {
        return Arrays.asList(suites).indexOf(card.getSuite()) + 1;
    }
}
