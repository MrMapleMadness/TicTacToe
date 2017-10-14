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
     * <bold>NOTE:<bold/> This is not the individual game, this is multiple games.
     */
    private void gameLoop() {
        while (!quit) {
            board.printBoard();

            board.setTile(board.getInput(), currentPlayer.getPiece());

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

        System.out.println("Congratulations " + currentPlayer.getName() + "!");
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
                    setNewGame();
                    done = true;
                    break;
                case "n":
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
        board = new Board();
        player1 = new Player(Piece.X, "Crosses");
        player2 = new Player(Piece.O, "Naughts");
        currentPlayer = player1;
    }

}
