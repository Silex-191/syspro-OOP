package blackpackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {
    private static class TestParticipant extends Participant {
        public TestParticipant(String displayName) {
            super(displayName);
        }
    }

    private Participant participant;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        participant = new TestParticipant("Test Player");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testInitialState() {
        assertEquals(0, participant.getScoreWins(), "Initial win score must be 0");
        assertNotNull(participant.getHand(), "Hand must be initialized upon creation");
        assertTrue(participant.getHand().getCards().isEmpty(), "Initial hand must be empty");
    }

    @Test
    void testAddWinIncrementsScoreWins() {
        participant.addWin();
        participant.addWin();

        assertEquals(2, participant.getScoreWins(),
                "addWin() must increment the score by 1 each time it is called");
    }

    @Test
    void testTakeCardFromNonEmptyDeck() {
        Card targetCard = new Card(Suit.SPADES, Rank.ACE);
        List<Card> predefinedCards = List.of(targetCard);
        DeckOfCards deck = new DeckOfCards(predefinedCards);

        participant.takeCard(deck);

        assertEquals(1, participant.getHand().getCards().size(),
                "Hand must contain exactly 1 card after taking one");
        assertEquals(targetCard, participant.getHand().getCards().getFirst(),
                "The drawn card must match the top card of the deck");
        assertTrue(outContent.toString().isEmpty(),
                "No console output should be generated when the deck is not empty");
    }

    @Test
    void testTakeCardFromEmptyDeckTriggersNewDeck() {
        List<Card> emptyCards = new ArrayList<>();
        DeckOfCards emptyDeck = new DeckOfCards(emptyCards); // Deck has 0 cards

        participant.takeCard(emptyDeck);
        String output = outContent.toString();

        assertEquals(1, participant.getHand().getCards().size(),
                "Participant must successfully draw a card even if the initial deck was empty");
        assertTrue(output.contains("В колоде больше нет карт"),
                "The system must print a warning when drawing from an empty deck");
    }

    @Test
    void testResetHandClearsCards() {
        DeckOfCards deck = new DeckOfCards(List.of(new Card(Suit.HEARTS, Rank.TEN)));
        participant.takeCard(deck);
        assertFalse(participant.getHand().getCards().isEmpty(), "Hand must not be empty before reset");

        participant.resetHand();

        assertTrue(participant.getHand().getCards().isEmpty(), "Hand must be completely empty after calling resetHand()");
    }

    @Test
    void testGetScoreDelegatesToHand() {
        DeckOfCards deck = new DeckOfCards(List.of(new Card(Suit.CLUBS, Rank.NINE)));
        participant.takeCard(deck);

        int score = participant.getScore();

        assertEquals(9, score, "Participant score must correctly reflect the score of the hand");
    }

    @Test
    void testLastCardsDelegatesToHand() {
        Card card = new Card(Suit.DIAMONDS, Rank.QUEEN);
        DeckOfCards deck = new DeckOfCards(List.of(card));
        participant.takeCard(deck);

        String lastCardString = participant.lastCards();

        assertEquals(card.toString(), lastCardString, "lastCards() must return the string representation of the last drawn card");
    }

    @Test
    void testToStringFormatting() {
        Card card = new Card(Suit.SPADES, Rank.EIGHT);
        DeckOfCards deck = new DeckOfCards(List.of(card));
        participant.takeCard(deck);

        String expectedString = "\tTest Player: [" + card.toString() + "] -> 8";
        String actualString = participant.toString();

        assertEquals(expectedString, actualString, "toString() must correctly format the display name, hand, and score");
    }
}