package blackpackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void testGetHiddenHandStringWhenHandIsEmpty() {
        String actualString = dealer.getHiddenHandString();
        assertEquals("[]", actualString, "An empty hand must return the string '[]'");
    }

    @Test
    void testGetHiddenHandStringWhenHandHasCards() {
        Card firstCard = new Card(Suit.SPADES, Rank.ACE);
        Card secondCard = new Card(Suit.HEARTS, Rank.TEN);

        dealer.getHand().addCard(firstCard);
        dealer.getHand().addCard(secondCard);

        String expectedDisplayName = "Карты дилера";
        String expectedHiddenMarker = "<закрытая карта>";
        String expectedString = "\t" + expectedDisplayName + ": ["
                + firstCard.toString() + ", " + expectedHiddenMarker + "]";

        String actualString = dealer.getHiddenHandString();
        assertEquals(expectedString, actualString,
                "The hidden hand string must reveal only the first card");
    }

    @Test
    void testDealerInitializesWithCorrectDisplayName() {
        Card card = new Card(Suit.DIAMONDS, Rank.SEVEN);
        dealer.getHand().addCard(card);

        String fullHandString = dealer.toString();

        assertTrue(fullHandString.contains("Карты дилера"),
                "The dealer's display name must be set correctly in the constructor");
    }
}