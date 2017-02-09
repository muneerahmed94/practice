package other.fahd.cards.week8;

import java.util.ArrayList;

/**
 * Created by Muneer on 05-02-2017.
 */
public class Player {
    private ArrayList<Card> cardsList;
    private int score;

    public Player() {
        this.cardsList = new ArrayList<>();
        this.score = 0;
    }

    public ArrayList<Card> getCardsList() {
        return cardsList;
    }

    public void setCardsList(ArrayList<Card> cardsList) {
        this.cardsList = cardsList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
