package blackpackage;

/**
 * Represents the dealer in the Blackjack game.
 * Inherits common behavior from the {@link Participant} class and adds
 * specific functionality for displaying a partially hidden hand.
 */
public class Dealer extends Participant {
    /**
     * Constructs a new {@code Dealer} with a predefined Russian display name.
     */
    public Dealer() {
        super("Карты дилера");
    }

    /**
     * Retrieves a string representation of the dealer's hand with the second card hidden.
     * This simulates the standard Blackjack rule where the dealer has one face-up
     * and one face-down card. The string includes localized Russian text indicating
     * the hidden card.
     *
     * @return a formatted string showing only the dealer's first card and a hidden card marker,
     *         or "[]" if the hand is empty
     */
    public String getHiddenHandString() {
        if (hand.getCards().isEmpty()) {
            return "[]";
        }
        return "\t" + displayName + ": [" + hand.getCards().getFirst() + ", <закрытая карта>]";
    }
}