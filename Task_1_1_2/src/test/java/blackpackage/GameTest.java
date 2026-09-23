package blackpackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testPlayerBustsAndLoses() {
        List<Card> predefinedCards = List.of(
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.HEARTS, Rank.SIX),
                new Card(Suit.DIAMONDS, Rank.TEN),
                new Card(Suit.CLUBS, Rank.SEVEN),
                new Card(Suit.SPADES, Rank.KING)
        );
        DeckOfCards deck = new DeckOfCards(predefinedCards);

        Scanner mockScanner = new Scanner("1\n0\n");
        Game game = new Game(deck, mockScanner);

        game.startRound();
        String output = outContent.toString();

        assertTrue(output.contains("Вы проиграли!"), "The output must indicate that the player lost by busting");
        assertTrue(output.contains("Ваш счет: 26"), "The output must show the correct busted score of 26");
    }

    @Test
    void testPlayerHitsExactlyTwentyOneAndWins() {
        List<Card> predefinedCards = List.of(
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.DIAMONDS, Rank.TEN),
                new Card(Suit.CLUBS, Rank.SEVEN),
                new Card(Suit.SPADES, Rank.NINE)
        );
        DeckOfCards deck = new DeckOfCards(predefinedCards);

        Scanner mockScanner = new Scanner("1\n0\n");
        Game game = new Game(deck, mockScanner);

        game.startRound();
        String output = outContent.toString();

        assertTrue(output.contains("Вы выиграли!"), "The output must indicate that the player won instantly by hitting 21");
        assertTrue(output.contains("Ваш счет: 21"), "The output must show the exact winning score of 21");
    }

    @Test
    void testDealerBustsAndPlayerWins() {
        List<Card> predefinedCards = List.of(
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.HEARTS, Rank.NINE),
                new Card(Suit.DIAMONDS, Rank.TEN),
                new Card(Suit.CLUBS, Rank.SIX),
                new Card(Suit.SPADES, Rank.KING)
        );
        DeckOfCards deck = new DeckOfCards(predefinedCards);

        Scanner mockScanner = new Scanner("0\n0\n");
        Game game = new Game(deck, mockScanner);

        game.startRound();
        String output = outContent.toString();

        assertTrue(output.contains("Дилер проиграл!"), "The output must indicate that the dealer busted and lost");
        assertTrue(output.contains("счет дилера: 26"), "The output must show the dealer's busted score of 26");
    }

    @Test
    void testStartRoundReturnsFalseWhenPlayerOptsOut() {
        List<Card> predefinedCards = List.of(
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.TEN),
                new Card(Suit.CLUBS, Rank.TEN)
        );
        DeckOfCards deck = new DeckOfCards(predefinedCards);

        Scanner mockScanner = new Scanner("0\n0\n");
        Game game = new Game(deck, mockScanner);

        boolean willContinue = game.startRound();

        assertFalse(willContinue, "startRound() must return false when the user inputs '0' to quit");
    }
}