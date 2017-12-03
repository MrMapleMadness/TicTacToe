import java.util.Scanner;

/**
 * The main game class. This class controls the whole game logic and contains the
 * main game loop. It creates the players and the board and runs the game.
 *
 * @author Angus Clinch
 */
class Game {
    //GUI
    private Output output;
    //LOGIC
    private static final String GAME_NAME = "eCase and SPIRE";
    private Board board;
    private boolean quit;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    /**
     * The constructor of the game, this sets up the board and begins the
     * main game loop.
     */
    Game(Output output) {
        this.output = output;
        String[] names = output.setNewGame();
        setNewGame(names[0], names[1]);
        gameLoop();
    }

    /**
     * The main game loop, this will run until the players want to quit.
     * <p>
     * <b>NOTE:</b> This is not the individual game, this is multiple games.
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
        if (output.winner(currentPlayer, player1, player2))
            board = new Board(output);
        else
            quit = true;
    }

    /**
     * Switches the current player
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
     * first. This overloaded allows players names to be added from the
     * calling method for the GUI.
     */
    private void setNewGame(String playerOneName, String playerTwoName) {
        player1 = new Player(Piece.X, playerOneName);
        player2 = new Player(Piece.O, playerTwoName);
        board = new Board(output);
        currentPlayer = player1;
    }

}
