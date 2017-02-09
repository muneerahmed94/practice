package other.fahd.cards.week7;

/**
 * Created by Muneer on 09-02-2017.
 */
public class Test {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Card card;
        for(int i = 0; i < 52; i++) {
            card = deck.dealCard();
            card.display();
        }
    }
}
