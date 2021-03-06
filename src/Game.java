import java.util.Scanner;

/**
 * The main game class. This class controls the whole game and contains the
 * main game loop. It creates the players and the board and runs the game.
 */
class Game {

    private Board board;
    private boolean quit;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    /**
     * The constructor of the game, this sets up the board and begins the
     * main game loop.
     */
    Game() {
        setNewGame();
        gameLoop();
    }

    /**
     * The main game loop, this will run until the players want to quit.
     *
     * <p>
     * Note: This is not the individual game, this is multiple games.
     * </p>
     */
    private void gameLoop() {
        while (!quit) {
            board.printBoard();
            board.setTile(board.getInput(currentPlayer), currentPlayer.getPiece());

            if (board.hasWinner()) {
                board.printBoard();
                winner();
            }

            changePlayer();
        }
    }

    /**
     * This runs once a winner is detected, it congratulates the player and
     * then asks if the players want to play again.
     */
    private void winner() {
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        currentPlayer.winsGame();

        System.out.println("Congratulations " + currentPlayer.getName() + "!");
        System.out.print(player1.getName() + ": " + player1.getScore() + " | ");
        System.out.println(player2.getName() + ": " + player2.getScore());
        System.out.println("Would you like to play again?");

        while (!done) {
            System.out.println("Yes | No");
            String input;

            try {
                input = sc.next().toLowerCase().substring(0, 1);
            } catch (StringIndexOutOfBoundsException e) {
                input = "failed";
            }

            switch (input) {
                case "y":
                    board = new Board();
                    done = true;
                    break;
                case "n":
                    System.out.println("Thanks for playing!");
                    quit = true;
                    done = true;
                    break;
                default:
                    System.out.println("Sorry, I didn't understand that. Please try again.");
                    break;
            }
        }
    }

    /**
     * Changes the player to the other player
     */
    private void changePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    /**
     * This creates the initial environment for the game including
     * setting up the board and players. This also controls who plays
     * first.
     */
    private void setNewGame() {
        System.out.println("===== Welcome to Naughts and Crosses =====");
        System.out.println("This game was created by Angus Clinch");

        player1 = new Player(Piece.X, "Crosses");
        player2 = new Player(Piece.O, "Naughts");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the name for Player One (Crosses)");
        player1.setName(scanner.next());

        System.out.println("Please enter the name for Player Two (Naughts)");
        player2.setName(scanner.next());

        System.out.println("Thank you, now let's play!");

        board = new Board();
        currentPlayer = player1;
    }

}
