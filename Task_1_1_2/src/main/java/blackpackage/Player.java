package blackpackage;

/**
 * Represents a player in the Blackjack game.
 * This class manages the player's current hand of cards and keeps track of their total number of wins.
 */
public class Player {
    private final Hand hand = new Hand();
    private int scoreWins = 0;

    /**
     * Draws a card from the specified deck and adds it to the player's hand.
     *
     * @param cards the {@link DeckOfCards} from which to draw a card
     */
    public void takeCard(DeckOfCards cards) {
        hand.getCard(cards);
    }

    /**
     * Clears all cards from the player's hand.
     * This is typically called at the end of a round to prepare for a new game.
     */
    public void resetHand() {
        hand.clearCard();
    }

    /**
     * Increments the player's total win count by one.
     */
    public void addWin() {
        scoreWins++;
    }

    /**
     * Retrieves the total number of rounds the player has won.
     *
     * @return the integer count of the player's total wins
     */
    public int getScoreWins() {
        return scoreWins;
    }

    /**
     * Retrieves the current numerical score of the player's hand.
     *
     * @return the calculated integer score of the hand
     */
    public int getScore() {
        return hand.getScore();
    }

    /**
     * Retrieves a string representation of the most recently drawn card in the player's hand.
     *
     * @return a formatted string of the last card added to the hand
     */
    public String lastCards() {
        return hand.lastCard();
    }

    /**
     * Returns a string representation of the player's current hand and score.
     * The string includes a localized Russian prefix indicating "Player's hand".
     *
     * @return a formatted string detailing the contents and score of the player's hand
     */
    @Override
    public String toString() {
        return "\tВаши карты: [" + hand + "] => " + getScore();
    }
}
