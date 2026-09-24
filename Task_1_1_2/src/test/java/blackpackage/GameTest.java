package blackpackage;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
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

        game.startGame();

        assertEquals(26, game.playerGetScore(),
                "Player's score must be 26");
        assertEquals(17, game.dealerGetScore(),
                "Dealer's score must be 17");
        assertEquals(0, game.playerGetScoreWins(),
                "Player's wins must be 0");
        assertEquals(1, game.dealerGetScoreWins(),
                "Dealer's wins must be 1");
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

        assertEquals(21, game.playerGetScore(),
                "Player's score must be 21");
        assertEquals(17, game.dealerGetScore(),
                "Dealer's score must be 17");
        assertEquals(1, game.playerGetScoreWins(),
                "Player's wins must be 1");
        assertEquals(0, game.dealerGetScoreWins(),
                "Dealer's wins must be 0");
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

        assertEquals(19, game.playerGetScore(),
                "Player's score must be 19");
        assertEquals(26, game.dealerGetScore(),
                "Dealer's score must be 26");
        assertEquals(1, game.playerGetScoreWins(),
                "Player's wins must be 1");
        assertEquals(0, game.dealerGetScoreWins(),
                "Dealer's wins must be 0");
    }

    @Test
    void testPlayerWinsByHigherScore() {
        List<Card> predefinedCards = List.of(
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.HEARTS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.CLUBS, Rank.EIGHT)
        );
        DeckOfCards deck = new DeckOfCards(predefinedCards);

        Scanner mockScanner = new Scanner("0\n0\n");
        Game game = new Game(deck, mockScanner);

        game.startRound();

        assertEquals(20, game.playerGetScore(),
                "Player's score must be 20");
        assertEquals(18, game.dealerGetScore(),
                "Dealer's score must be 18");
        assertEquals(1, game.playerGetScoreWins(),
                "Player's wins must be 1");
        assertEquals(0, game.dealerGetScoreWins(),
                "Dealer's wins must be 0");
    }

    @Test
    void testDealerWinsByHigherScore() {
        List<Card> predefinedCards = List.of(
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.CLUBS, Rank.NINE)
        );
        DeckOfCards deck = new DeckOfCards(predefinedCards);

        Scanner mockScanner = new Scanner("0\n0\n");
        Game game = new Game(deck, mockScanner);

        game.startRound();

        assertEquals(17, game.playerGetScore(),
                "Player's score must be 17");
        assertEquals(19, game.dealerGetScore(),
                "Dealer's score must be 19");
        assertEquals(0, game.playerGetScoreWins(),
                "Player's wins must be 0");
        assertEquals(1, game.dealerGetScoreWins(),
                "Dealer's wins must be 1");
    }

    @Test
    void testGameEndsInTie() {
        List<Card> predefinedCards = List.of(
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.HEARTS, Rank.EIGHT),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.CLUBS, Rank.EIGHT)
        );
        DeckOfCards deck = new DeckOfCards(predefinedCards);

        Scanner mockScanner = new Scanner("0\n0\n");
        Game game = new Game(deck, mockScanner);

        game.startRound();

        assertEquals(18, game.playerGetScore(),
                "Player's score must be 18");
        assertEquals(18, game.dealerGetScore(),
                "Dealer's score must be 18");
        assertEquals(0, game.playerGetScoreWins(),
                "Player's wins must be 0");
        assertEquals(0, game.dealerGetScoreWins(),
                "Dealer's wins must be 0");
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

        assertFalse(willContinue,
                "startRound() must return false when the user inputs '0' to quit");
    }
}