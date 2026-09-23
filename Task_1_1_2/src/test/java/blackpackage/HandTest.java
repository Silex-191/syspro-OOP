package blackpackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    void testAddCardAndGetCards() {
        Card card = new Card(Suit.SPADES, Rank.TEN);

        hand.addCard(card);
        List<Card> currentCards = hand.getCards();

        assertEquals(1, currentCards.size(), "Hand must contain exactly 1 card after adding one");
        assertEquals(card, currentCards.getFirst(), "The retrieved card must match the added card");
    }

    @Test
    void testClearCard() {
        hand.addCard(new Card(Suit.HEARTS, Rank.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE));

        hand.clearCard();

        assertTrue(hand.getCards().isEmpty(), "Hand must be empty after calling clearCard()");
    }

    @Test
    void testGetScoreWithoutAces() {
        hand.addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        hand.addCard(new Card(Suit.SPADES, Rank.SEVEN));

        int score = hand.getScore();

        assertEquals(17, score, "Score of TEN and SEVEN must be 17");
    }

    @Test
    void testGetScoreWithAceNotBusting() {
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE));

        int score = hand.getScore();

        assertEquals(20, score, "Ace should be counted as 11 when total score is 21 or less");
    }

    @Test
    void testGetScoreWithAceBusting() {
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE));
        hand.addCard(new Card(Suit.SPADES, Rank.FIVE));

        int score = hand.getScore();

        assertEquals(15, score, "Ace should be reduced to 1 if the score exceeds 21");
    }

    @Test
    void testGetScoreWithMultipleAces() {
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.NINE));

        int score = hand.getScore();

        assertEquals(21, score,
                "Multiple aces must be handled correctly to prevent busting if possible");
    }

    @Test
    void testLastCardWithEmptyHand() {
        String result = hand.lastCard();

        assertEquals("Рука пуста", result,
                "Empty hand must return the specific localized empty string");
    }

    @Test
    void testLastCardWithMultipleCards() {
        Card firstCard = new Card(Suit.SPADES, Rank.TWO);
        Card secondCard = new Card(Suit.HEARTS, Rank.KING);
        hand.addCard(firstCard);
        hand.addCard(secondCard);

        String result = hand.lastCard();

        assertEquals(secondCard.toString(), result,
                "lastCard() must return the string representation of the most recently added card");
    }

    @Test
    void testToStringWithEmptyHand() {
        String result = hand.toString();

        assertEquals("Рука пуста", result,
                "Empty hand toString() must return the localized empty string");
    }

    @Test
    void testToStringWithMultipleCards() {
        Card card1 = new Card(Suit.SPADES, Rank.JACK);
        Card card2 = new Card(Suit.HEARTS, Rank.QUEEN);
        hand.addCard(card1);
        hand.addCard(card2);

        String expected = card1.toString() + ", " + card2.toString();

        String result = hand.toString();

        assertEquals(expected, result, "Cards must be formatted as a comma-separated list");
    }
}