package other.fahd.cards.week8;

import java.util.Scanner;

/**
 * Created by Muneer on 04-02-2017.
 */
public class Test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of games to be played: ");
        int noOfGames = sc.nextInt();

        int player1WinsCount = 0;
        int player2WinsCount = 0;
        int drawCount = 0;

        for (int game = 1; game <= noOfGames; game++) {
            System.out.println("\nGame " + game + " starts...");

            Deck deck = new Deck();
            deck.shuffle();

            Player player1 = new Player();
            Player player2 = new Player();

            for (int i = 0; i < 26; i++) {
                player1.getCardsList().add(deck.dealCard());
                player2.getCardsList().add(deck.dealCard());
            }

            for (int i = 0; i < 26; i++) {
                System.out.println("\nRound: " + i);

                Card player1Card = player1.getCardsList().remove(0);
                Card player2Card = player2.getCardsList().remove(0);

                if (player1Card.compareTo(player2Card) > 0) {
                    player1.setScore(player1.getScore() + 1);
                } else {
                    player2.setScore(player2.getScore() + 1);
                }

                String winner = (player1Card.compareTo(player2Card) > 0) ? "Player 1" : "Player 2";
                System.out.println(player1Card.getDisplayString() + " vs " + player2Card.getDisplayString() + " => " + winner);
            }

            System.out.println("\nGame " + game + " ends...");

            System.out.println("Player 1 score: " + player1.getScore());
            System.out.println("Player 2 score: " + player2.getScore());

            if (player1.getScore() > player2.getScore()) {
                System.out.println("Player 1 wins");
                player1WinsCount++;
            } else if (player1.getScore() < player2.getScore()) {
                System.out.println("Player 2 wins");
                player2WinsCount++;
            } else {
                System.out.println("Draw");
                drawCount++;
            }
        }

        System.out.println("\nSummary");
        System.out.println("No of games played: " + noOfGames);
        System.out.println("No of games won by Player 1: " + player1WinsCount);
        System.out.println("No of games won by Player 2: " + player2WinsCount);
        System.out.println("No of games Drawn: " + drawCount);
    }
}
