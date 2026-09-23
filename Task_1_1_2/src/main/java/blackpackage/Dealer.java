package blackpackage;

/**
 * Represents the dealer in the Blackjack game.
 * This class manages the dealer's hand, tracks their total wins, and implements
 * standard dealer drawing logic (hitting until the score reaches 17).
 */
public class Dealer {
    private final Hand hand = new Hand();
    private int scoreWins = 0;

    /**
     * Draws a card from the specified deck and adds it to the dealer's hand.
     *
     * @param cards the {@link DeckOfCards} from which to draw a card
     */
    public void takeCard(DeckOfCards cards) {
        hand.getCard(cards);
    }

    /**
     * Retrieves a string representation of the dealer's hand with the second card hidden.
     * This simulates the standard Blackjack setup where the dealer has one "hole" (face-down) card.
     * The string includes localized Russian text indicating a hidden card.
     *
     * @return a formatted string showing only the dealer's first card and a hidden card marker,
     *         or "[]" if the hand is empty
     */
    public String getHiddenHandString() {
        if (hand.getCards().isEmpty()) {
            return "[]";
        }
        return "\tКарты дилера: [" + hand.getCards().getFirst() + ", <закрытая карта>]";
    }

    /**
     * Clears all cards from the dealer's hand.
     * This is typically called at the end of a round to prepare for a new game.
     */
    public void resetHand() {
        hand.clearCard();
    }

    /**
     * Increments the dealer's total win count by one.
     */
    public void addWin() {
        scoreWins++;
    }

    /**
     * Retrieves the total number of rounds the dealer has won.
     *
     * @return the integer count of the dealer's total wins
     */
    public int getScoreWins() {
        return scoreWins;
    }

    /**
     * Retrieves the current numerical score of the dealer's hand.
     *
     * @return the calculated integer score of the hand
     */
    public int getScore() {
        return hand.getScore();
    }

    /**
     * Retrieves a string representation of the most recently drawn card in the dealer's hand.
     *
     * @return a formatted string of the last card added to the hand
     */
    public String lastCards() {
        return hand.lastCard();
    }

    /**
     * Returns a string representation of the dealer's fully revealed hand and score.
     * The string includes a localized Russian prefix indicating "Dealer's hand".
     *
     * @return a formatted string detailing all cards and the final score of the dealer's hand
     */
    @Override
    public String toString() {
        return "\tКарты дилера: [" + hand + "] -> " + getScore();
    }
}
