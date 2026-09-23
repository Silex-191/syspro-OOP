package blackpackage;

/**
 * Represents a standard playing card with a specific suit and rank.
 * This class is immutable, meaning once a card is created, its suit and rank cannot be changed.
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    /**
     * Constructs a new {@code Card} with the specified suit and rank.
     *
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Retrieves the numerical value of the card's rank.
     *
     * @return the integer value associated with the card's rank
     */
    public int getRankValue() {
        return rank.getValue();
    }

    /**
     * Checks whether this card is an Ace.
     *
     * @return {@code true} if the card's rank is an Ace, {@code false} otherwise
     */
    public boolean isAce() {
        return rank == Rank.ACE;
    }

    /**
     * Returns a string representation of the card.
     * The returned string typically includes the rank name, suit name, and its numerical value.
     *
     * @return a formatted string representing the card
     */
    @Override
    public String toString() {
        return rank.getName() + " " + suit.getName() + " (" + getRankValue() + ")";
    }
}
