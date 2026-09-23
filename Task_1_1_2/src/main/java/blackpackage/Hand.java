package blackpackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player's or dealer's hand of cards in the game.
 * This class handles drawing cards, calculating the total score (with specific
 * Blackjack logic for Aces), and formatting the hand's contents for display.
 */
public class Hand {
    private final List<Card> cards = new ArrayList<>();

    /**
     * Adds a specific card to this hand.
     *
     * @param card the {@link Card} to be added to the hand
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Removes all cards from the hand.
     */
    public void clearCard() {
        cards.clear();
    }

    /**
     * Calculates the total numerical score of the hand.
     * This method applies standard Blackjack scoring logic: Aces are initially
     * valued at 11, but if the total score exceeds 21, the value of each Ace
     * is reduced to 1 (by subtracting 10) to prevent the hand from busting.
     *
     * @return the calculated integer score of the hand
     */
    public int getScore() {
        int score = 0;
        int aces = 0;

        for (Card card : cards) {
            score += card.getRankValue();
            if (card.isAce()) {
                aces++;
            }
        }

        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }

        return score;
    }

    /**
     * Retrieves a string representation of the most recently drawn card in the hand.
     *
     * @return a formatted string of the last card, or "Рука пуста" (Hand is empty)
     *     if the hand contains no cards
     */
    public String lastCard() {
        if (cards.isEmpty()) {
            return "Рука пуста";
        }
        return cards.getLast().toString();
    }

    /**
     * Retrieves the list of cards currently held in the hand.
     *
     * @return a {@code List} of {@link Card} objects in this hand
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Returns a string representation of the entire hand.
     * The cards are formatted as a comma-separated list.
     *
     * @return a formatted string displaying all cards in the hand, or "Рука пуста"
     *     (Hand is empty) if the hand contains no cards
     */
    @Override
    public String toString() {
        if (cards.isEmpty()) {
            return "Рука пуста";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(cards.get(i).toString());
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}