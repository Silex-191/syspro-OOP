package blackpackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    void testRankValues() {
        assertEquals(2, Rank.TWO.getValue(), "Rank TWO must have a numeric value of 2");
        assertEquals(7, Rank.SEVEN.getValue(), "Rank SEVEN must have a numeric value of 7");

        assertEquals(10, Rank.TEN.getValue(), "Rank TEN must have a numeric value of 10");
        assertEquals(10, Rank.JACK.getValue(), "Rank JACK must have a numeric value of 10");
        assertEquals(10, Rank.QUEEN.getValue(), "Rank QUEEN must have a numeric value of 10");
        assertEquals(10, Rank.KING.getValue(), "Rank KING must have a numeric value of 10");

        assertEquals(11, Rank.ACE.getValue(), "Rank ACE must have a numeric value of 11");
    }

    @Test
    void testRankLocalizedNames() {
        assertEquals("Двойка", Rank.TWO.getName(), "Rank TWO must be named 'Двойка'");
        assertEquals("Десятка", Rank.TEN.getName(), "Rank TEN must be named 'Десятка'");
        assertEquals("Дама", Rank.QUEEN.getName(), "Rank QUEEN must be named 'Дама'");
        assertEquals("Туз", Rank.ACE.getName(), "Rank ACE must be named 'Туз'");
    }
}