package blackpackage;

/**
 * Represents a human player in the Blackjack game.
 * Inherits common participant behavior from the {@link Participant} class.
 */
public class Player extends Participant {
    /**
     * Constructs a new {@code Player} with a predefined Russian display name.
     */
    public Player() {
        super("Ваши карты");
    }
}