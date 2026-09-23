package blackpackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    void testGetRankValueForNumberCard() {
        Card card = new Card(Suit.HEARTS, Rank.SEVEN);
        assertEquals(7, card.getRankValue(), "The value of SEVEN must be 7");
    }

    @Test
    void testGetRankValueForFaceCard() {
        Card card = new Card(Suit.SPADES, Rank.KING);
        assertEquals(10, card.getRankValue(), "The value of a KING must be 10");
    }

    @Test
    void testGetRankValueForAce() {
        Card card = new Card(Suit.DIAMONDS, Rank.ACE);
        assertEquals(11, card.getRankValue(), "The value of an ACE must be 11");
    }

    @Test
    void testIsAceReturnsTrueForAceCard() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        assertTrue(card.isAce(), "Method isAce() must return true when the rank is ACE");
    }

    @Test
    void testIsAceReturnsFalseForNonAceCard() {
        Card card = new Card(Suit.HEARTS, Rank.TEN);
        assertFalse(card.isAce(), "Method isAce() must return false for any card other than ACE");
    }

    @Test
    void testToStringFormatting() {
        Suit testSuit = Suit.SPADES;
        Rank testRank = Rank.QUEEN;
        Card card = new Card(testSuit, testRank);
        String actualString = card.toString();
        String expectedString = testRank.getName() + " " + testSuit.getName() + " (" + testRank.getValue() + ")";
        assertEquals(expectedString, actualString, "The toString() format does not match the expected pattern");
    }
}