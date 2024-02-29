public class Card {
    
    //to be changed to symbols later on
    String[] suites = {"diamonds", "cloves", "hearts", "spades"};
    //numbers and faces
    String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    String[] currentCard = new String[2];


    public Card(String value, String suite) {
        this.currentCard[0] = value;
        this.currentCard[1] = suite;
    }
}
