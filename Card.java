public class Card {
    
    //tracks suite and value
    String suite, value;

    //to be changed to symbols later on
    String[] suites = {"diamonds", "hearts", "clubs", "spades"};
    //numbers and faces
    String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    //card consttrcutor
    public Card(String value, String suite) {
        this.value = value;
        this.suite = suite;
    }

    //getters
    public String getValue() {
        return this.value;
    }

    public String getSuite() {
        return this.suite;
    }

    //returns number value of string value
    public int getNumberValue() {
        if (value.equals("A")) {
            return 11;
        }
        if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10;
        }
        else {
            Integer x = Integer.valueOf(value);
            return x;
        }
    }
}
