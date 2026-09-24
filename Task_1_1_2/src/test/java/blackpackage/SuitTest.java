package blackpackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuitTest {

    @Test
    void testSuitLocalizedNames() {
        assertEquals("Пики", Suit.SPADES.getName(), "Suit SPADES must be named 'Пики'");
        assertEquals("Червы", Suit.HEARTS.getName(), "Suit HEARTS must be named 'Червы'");
        assertEquals("Бубны", Suit.DIAMONDS.getName(), "Suit DIAMONDS must be named 'Бубны'");
        assertEquals("Крести", Suit.CLUBS.getName(), "Suit CLUBS must be named 'Крести'");
    }
}