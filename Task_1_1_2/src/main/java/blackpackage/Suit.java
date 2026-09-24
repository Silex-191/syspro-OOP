package blackpackage;

/**
 * Represents the four standard suits in a deck of playing cards.
 * Each suit is associated with a localized display name (in Russian).
 */
public enum Suit {
    SPADES("Пики"),
    HEARTS("Червы"),
    DIAMONDS("Бубны"),
    CLUBS("Крести");

    private final String name;

    /**
     * Constructs a {@code Suit} with the specified localized name.
     *
     * @param name the Russian display name of the suit
     */
    Suit(String name) {
        this.name = name;
    }

    /**
     * Retrieves the localized display name of the suit.
     *
     * @return a {@code String} representing the Russian name of the suit
     */
    public String getName() {
        return name;
    }
}
