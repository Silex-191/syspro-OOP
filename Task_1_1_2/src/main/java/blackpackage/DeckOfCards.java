package blackpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a standard deck of playing cards.
 * This class handles the creation, shuffling, and drawing of cards from the deck.
 */
public class DeckOfCards {
    private final List<Card> cards = new ArrayList<>();

    /**
     * Constructs a new {@code DeckOfCards}.
     * Upon creation, the deck is automatically populated with a standard set
     * of 52 cards and shuffled.
     */
    public DeckOfCards() {
        newDeck();
    }

    /**
     * Resets the deck to a full standard set of 52 cards.
     * This method clears any remaining cards, generates a new card for every
     * combination of {@link Suit} and {@link Rank}, and then shuffles the deck.
     */
    public void newDeck() {
        cards.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    /**
     * Randomizes the order of the cards currently remaining in the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws the top card from the deck.
     * The card is removed from the deck upon being drawn.
     *
     * @return the {@link Card} drawn from the top of the deck,
     * or {@code null} if the deck is empty
     */
    public Card getCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.removeFirst();
    }
}