package blackpackage;

import java.util.Scanner;

/**
 * The main controller for the Blackjack game.
 * This class orchestrates the game loop, manages the interactions between the
 * {@link Player}, {@link Dealer}, and {@link DeckOfCards}, and handles
 * console-based user input and localized (Russian) text output.
 */
public class Game {
    private int round = 1;
    private final Player player = new Player();
    private final Dealer dealer = new Dealer();

    private final DeckOfCards deck;
    private final Scanner scanner;

    /**
     * Default constructor for the game.
     * Initializes the game with a standard, randomized 52-card deck
     * and a scanner listening to standard system input.
     */
    public Game() {
        this.deck = new DeckOfCards();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructs a new {@code Game} with a specific deck and scanner.
     * This constructor is intended for Dependency Injection, primarily useful
     * for Unit Testing by providing predefined cards and mocked user input.
     *
     * @param deck    the {@link DeckOfCards} to be used in the game
     * @param scanner the {@link Scanner} used to read user input
     */
    public Game(DeckOfCards deck, Scanner scanner) {
        this.deck = deck;
        this.scanner = scanner;
    }

    /**
     * Retrieves the total number of rounds the player has won.
     * This is a forwarding method that delegates the call to the internal {@link Player} instance.
     *
     * @return the integer count of the player's total wins
     */
    public int playerGetScoreWins() {
        return player.getScoreWins();
    }

    /**
     * Retrieves the total number of rounds the dealer has won.
     * This is a forwarding method that delegates the call to the internal {@link Dealer} instance.
     *
     * @return the integer count of the dealer's total wins
     */
    public int dealerGetScoreWins() {
        return dealer.getScoreWins();
    }

    /**
     * Retrieves the current numerical score of the player's hand.
     * This is a forwarding method that delegates the call to the internal {@link Player} instance.
     *
     * @return the calculated integer score of the player's hand
     */
    public int playerGetScore() {
        return player.getScore();
    }

    /**
     * Retrieves the current numerical score of the dealer's hand.
     * This is a forwarding method that delegates the call to the internal {@link Dealer} instance.
     *
     * @return the calculated integer score of the dealer's hand
     */
    public int dealerGetScore() {
        return dealer.getScore();
    }

    /**
     * Initializes and starts the Blackjack game session.
     * This method prints a welcome message to the console and immediately
     * begins the first round.
     */
    public void startGame() {
        System.out.println("Добро пожаловать в Блэкджек!");
        while (true) {
            if (!startRound()) {
                System.out.println("Игра окончена! Ваши победы: " + player.getScoreWins()
                        + ", победы дилера: "
                        + dealer.getScoreWins());
                break;
            }
        }
    }

    /**
     * Executes a single round of Blackjack.
     * Note: This method calls itself recursively if the player chooses to continue playing.
     */
    public boolean startRound() {
        System.out.println("Раунд " + round++);

        player.resetHand();
        dealer.resetHand();

        player.takeCard(deck);
        player.takeCard(deck);

        dealer.takeCard(deck);
        dealer.takeCard(deck);

        System.out.println("Дилер раздал карты");
        System.out.println(player);
        System.out.println(dealer.getHiddenHandString());

        System.out.println("Ваш ход\n-------");

        boolean roundGoesOn = true;

        while (true) {
            System.out.print("Введите '1', чтобы взять карту, и '0', чтобы остановиться: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                player.takeCard(deck);
                System.out.println("Вы открыли карту " + player.lastCards());
                System.out.println(player);
                System.out.println(dealer.getHiddenHandString());

                if (player.getScore() > 21) {
                    dealer.addWin();
                    System.out.println("Вы проиграли! Ваш счет: " + player.getScore()
                            + ", счет дилера: "
                            + dealer.getScore());
                    roundGoesOn = false;
                    break;
                } else if (player.getScore() == 21) {
                    player.addWin();
                    System.out.println("Вы выиграли! Ваш счет: " + player.getScore()
                            + ", счет дилера: "
                            + dealer.getScore());
                    roundGoesOn = false;
                    break;
                }
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("Некорректный ввод! Попробуйте снова:");
            }
        }

        if (roundGoesOn) {
            System.out.println("Дилер открывает закрытую карту " + dealer.lastCards());
            System.out.println(player);
            System.out.println(dealer);

            System.out.println("Ход дилера\n-------");

            while (dealer.getScore() < 17) {
                dealer.takeCard(deck);
                System.out.println("Дилер открывает карту " + dealer.lastCards());
                System.out.println(player);
                System.out.println(dealer);
            }

            if (dealer.getScore() > 21) {
                player.addWin();
                System.out.println("Дилер проиграл! Ваш счет: " + player.getScore()
                        + ", счет дилера: "
                        + dealer.getScore());
            } else if (dealer.getScore() > player.getScore()) {
                dealer.addWin();
                System.out.println("Вы проиграли! Ваш счет: " + player.getScore()
                        + ", счет дилера: "
                        + dealer.getScore());
            } else if (dealer.getScore() < player.getScore()) {
                player.addWin();
                System.out.println("Вы выиграли! Ваш счет: " + player.getScore()
                        + ", счет дилера: "
                        + dealer.getScore());
            } else {
                System.out.println("Ничья! Ваш счет: " + player.getScore()
                        + ", счет дилера: " + dealer.getScore());
            }
        }

        System.out.print("Раунд окончен. Счет " + player.getScoreWins()
                + ":" + dealer.getScoreWins());
        if (player.getScoreWins() > dealer.getScoreWins()) {
            System.out.println(" - Вы лидируете!");
        } else if (player.getScoreWins() < dealer.getScoreWins()) {
            System.out.println(" - Дилер лидирует!");
        } else {
            System.out.println(" - Ничья!");
        }

        System.out.println("Хотите продолжить игру? (1 - да, 0 - нет): ");
        return (scanner.nextInt() == 1);
    }
}