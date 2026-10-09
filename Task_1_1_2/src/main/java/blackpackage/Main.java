package blackpackage;

/**
 * The entry point for the Blackjack console application.
 * This class contains the main method which initializes and starts the game session.
 */
public class Main {
    /**
     * The main method that serves as the entry point for the Java application.
     * It creates a new instance of {@link Game} and triggers the start of the game.
     *
     * @param args the command-line arguments (not utilized in this application)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}