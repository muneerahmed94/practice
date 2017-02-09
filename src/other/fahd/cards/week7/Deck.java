package other.fahd.cards.week7;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Muneer on 04-02-2017.
 */
public class Deck {
    ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                Card card = new Card(j, i);
                cards.add(card);
            }
        }
    }

    public void display() {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).display();
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 51; i >= 1; i--) {
            int j = random.nextInt(i + 1);
            //temp = i;
            Card tempCard = cards.get(i);
            //i = j;
            cards.set(i, cards.get(j));
            //j = temp;
            cards.set(j, tempCard);
        }
    }

    public Card dealCard() {
        if (cards.size() > 0) {
            return cards.remove(0);
        }
        return null;
    }
}
