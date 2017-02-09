package other.fahd.cards.week7;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muneer on 04-02-2017.
 */
public class Card implements Comparable<Card> {

    public static final Map<Integer, String> suitMap;
    public static final Map<Integer, String> cardMap;

    static {
        suitMap = new HashMap<Integer, String>();
        suitMap.put(1, "Diamonds");
        suitMap.put(2, "Clubs");
        suitMap.put(3, "Hearts");
        suitMap.put(4, "Spades");

        cardMap = new HashMap<Integer, String>();
        cardMap.put(1, "Ace");
        cardMap.put(2, "2");
        cardMap.put(3, "3");
        cardMap.put(4, "4");
        cardMap.put(5, "5");
        cardMap.put(6, "6");
        cardMap.put(7, "7");
        cardMap.put(8, "8");
        cardMap.put(9, "9");
        cardMap.put(9, "9");
        cardMap.put(10, "10");
        cardMap.put(11, "Jack");
        cardMap.put(12, "Queen");
        cardMap.put(13, "King");
    }

    private int displayValue;
    private int suit;

    public Card(int displayValue, int suit) {
        this.displayValue = displayValue;
        this.suit = suit;
    }

    public int getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(int displayValue) {
        this.displayValue = displayValue;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public void display() {
        System.out.println(getDisplayString());
    }

    public String getDisplayString() {
        return cardMap.get(displayValue) + " of " + suitMap.get(suit);
    }

    @Override
    public int compareTo(Card other) {
        if (this.displayValue > other.displayValue) {
            return 1;
        } else if (this.displayValue < other.displayValue) {
            return -1;
        } else {
            return (this.suit > other.suit) ? 1 : -1;
        }
    }
}
