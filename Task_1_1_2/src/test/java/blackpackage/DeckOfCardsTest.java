package blackpackage;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DeckOfCardsTest {
    @Test
    void testDefaultConstructorCreatesFullDeck() {
        DeckOfCards deck = new DeckOfCards();
        int cardCount = 0;

        while (deck.getCard() != null) {
            cardCount++;
        }

        assertEquals(52, cardCount, "A standard new deck must contain exactly 52 cards");
    }

    @Test
    void testPredefinedDeckConstructor() {
        Card card1 = new Card(Suit.SPADES, Rank.ACE);
        Card card2 = new Card(Suit.HEARTS, Rank.TEN);
        List<Card> riggedCards = List.of(card1, card2);

        DeckOfCards deck = new DeckOfCards(riggedCards);

        assertEquals(card1, deck.getCard(),
                "The first drawn card must match the first predefined card");
        assertEquals(card2, deck.getCard(),
                "The second drawn card must match the second predefined card");
    }

    @Test
    void testNewDeckResetsAndRefillsCards() {
        List<Card> singleCardList = List.of(new Card(Suit.CLUBS, Rank.TWO));
        DeckOfCards deck = new DeckOfCards(singleCardList);

        deck.newDeck();
        int cardCount = 0;
        while (deck.getCard() != null) {
            cardCount++;
        }

        assertEquals(52, cardCount,
                "The newDeck() method must refill the deck to exactly 52 cards");
    }

    @Test
    void testGetCardReturnsNullWhenEmpty() {
        List<Card> emptyList = new ArrayList<>();
        DeckOfCards deck = new DeckOfCards(emptyList);

        Card drawnCard = deck.getCard();

        assertNull(drawnCard, "Drawing from an empty deck must return null");
    }
}