import java.util.Scanner;

class Game {

    private Board board;
    private boolean quit;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    Game() {
        setNewGame();
        gameLoop();
    }

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

    private void changePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private void setNewGame() {
        board = new Board();
        player1 = new Player(Piece.X, "Crosses");
        player2 = new Player(Piece.O, "Naughts");
        currentPlayer = player1;
    }

}
