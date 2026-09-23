package blackpackage;

/**
 * An abstract base class representing a generic participant in the Blackjack game
 * (e.g., a Player or a Dealer).
 * This class encapsulates common state and behavior such as holding a hand of cards,
 * tracking total wins, and drawing cards from a deck.
 */
public abstract class Participant {
    protected final Hand hand = new Hand();
    protected int scoreWins = 0;
    protected final String displayName;

    /**
     * Constructs a new {@code Participant} with the specified display name.
     *
     * @param displayName the name used to identify this participant in console output
     */
    public Participant(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Draws a card from the specified deck and adds it to the participant's hand.
     * If the deck is empty, this method automatically generates a new, shuffled deck,
     * informs the user via the console, and draws a card from the new deck.
     *
     * @param cards the {@link DeckOfCards} from which to draw a card
     */
    public void takeCard(DeckOfCards cards) {
        Card card = cards.getCard();

        if (card == null) {
            System.out.println("В колоде больше нет карт. "
                    + "Дилер взял новую колоду и тщательно перемешал её.");
            cards.newDeck();
            card = cards.getCard();
        }

        hand.addCard(card); // Передаем готовую карту в руку
    }

    /**
     * Clears all cards from the participant's hand.
     * Typically called at the beginning of a new round.
     */
    public void resetHand() {
        hand.clearCard();
    }

    /**
     * Increments the participant's total win count by one.
     */
    public void addWin() {
        scoreWins++;
    }

    /**
     * Retrieves the total number of rounds the participant has won.
     *
     * @return the integer count of total wins
     */
    public int getScoreWins() {
        return scoreWins;
    }

    /**
     * Retrieves the current numerical score of the participant's hand.
     *
     * @return the calculated integer score of the hand
     */
    public int getScore() {
        return hand.getScore();
    }

    /**
     * Retrieves a string representation of the most recently drawn card in the hand.
     *
     * @return a formatted string of the last card added to the hand
     */
    public String lastCards() {
        return hand.lastCard();
    }

    /**
     * Retrieves the participant's current hand.
     *
     * @return the {@link Hand} object representing the participant's cards
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Returns a string representation of the participant's fully revealed hand and score.
     *
     * @return a formatted string detailing the participant's name, cards, and final score
     */
    @Override
    public String toString() {
        return "\t" + displayName + ": [" + hand + "] -> " + getScore();
    }
}