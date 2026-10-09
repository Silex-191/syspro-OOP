package blackpackage;

/**
 * Represents the rank of a standard playing card.
 * Each rank is associated with a localized display name (in Russian)
 * and a specific numerical value, typical for card games like Blackjack.
 */
public enum Rank {
    TWO("Двойка", 2),
    THREE("Тройка", 3),
    FOUR("Четверка", 4),
    FIVE("Пятерка", 5),
    SIX("Шестерка", 6),
    SEVEN("Семерка", 7),
    EIGHT("Восьмерка", 8),
    NINE("Девятка", 9),
    TEN("Десятка", 10),
    JACK("Валет", 10),
    QUEEN("Дама", 10),
    KING("Король", 10),
    ACE("Туз", 11);

    private final String name;
    private final int value;

    /**
     * Constructs a {@code Rank} with the specified localized name and numerical value.
     *
     * @param name  the localized display name of the rank (in Russian)
     * @param value the numerical value of the rank in the game
     */
    Rank(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Retrieves the localized display name of the rank.
     *
     * @return a {@code String} representing the Russian name of the rank
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the numerical value of the rank.
     * Face cards generally return 10, and an Ace returns 11.
     *
     * @return the integer value associated with this rank
     */
    public int getValue() {
        return value;
    }
}
