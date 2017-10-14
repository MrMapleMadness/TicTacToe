import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Defines a board that is used in the game Tic Tac Toe, this can be a 3 x 3 grid or bigger.
 *
 * @see <a href="https://www.wikiwand.com/en/Tic-tac-toe">Wikepedia article about Tic Tac Toe</a>
 */
class Board {

    private Piece[][] tiles;
    private static final int DIMENSIONS = 3;
    private Point lastMove;

    /**
     * Constructor for this class. It creates a new two dimensional array
     * to store the tile data and generates a clean board.
     */
    Board() {
        tiles = new Piece[DIMENSIONS][DIMENSIONS];
        generateCleanBoard();
    }

    /**
     * Get's input from the player. This method should be able to be
     * changed to any kind of input as long as it returns a valid point on the board.
     *
     * @return  A valid point on the board
     */
    Point getInput() {
        boolean done = false;
        int row = 0, col = 0;
        while (!done) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Please enter a row from 1 to " + DIMENSIONS);
                row = scanner.nextInt() - 1;

                System.out.println("Please enter a column from 1 to " + DIMENSIONS);
                col = scanner.nextInt() - 1;

                if (checkValid(row, col)) {
                    done = true;
                } else {
                    System.out.println("That was an invalid placement!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please only enter numbers.");
            }
        }
        lastMove = new Point(row, col);
        return lastMove;
    }

    /**
     * Checks the validity of the players move.
     *
     * @param row The row of the players requested input
     * @param col The column of the players requested input
     * @return If the placement of the piece is valid
     */
    private boolean checkValid(int row, int col) {
        return (row >= 0 && row < DIMENSIONS)
                && (col >= 0 && col < DIMENSIONS)
                && tiles[row][col] == Piece.BLANK;
    }

    /**
     * This places the tile at the requested location in the data array.
     *
     * @param point A point to place the piece
     * @param piece The type of piece to place
     */
    void setTile(Point point, Piece piece) {
        tiles[point.x][point.y] = piece;
    }

    /**
     * Cleans the board of all pieces by setting the blank piece.
     *
     * TODO: 14/10/2017 Improve this using streams
     */
    private void generateCleanBoard() {
        for (int i = 0; i < DIMENSIONS; i++) {
            for (int j = 0; j < DIMENSIONS; j++) {
                tiles[i][j] = Piece.BLANK;
            }
        }
    }

    /**
     * Prints the graphical representation of the board.
     * This could be changed to any type of GUI.
     *
     * @ TODO: 14/10/2017 Make this a graphical interface
     */
    void printBoard() {
        System.out.println("-------------");
        System.out.println("| " + tiles[0][0].getPieceCode() + " | " + tiles[0][1].getPieceCode() + " | " + tiles[0][2].getPieceCode() + " |");
        System.out.println("|---|---|---|");
        System.out.println("| " + tiles[1][0].getPieceCode() + " | " + tiles[1][1].getPieceCode() + " | " + tiles[1][2].getPieceCode() + " |");
        System.out.println("|---|---|---|");
        System.out.println("| " + tiles[2][0].getPieceCode() + " | " + tiles[2][1].getPieceCode() + " | " + tiles[2][2].getPieceCode() + " |");
        System.out.println("-------------");
    }

    /**
     * Checks to see if anyone has won the game.
     *
     * @ TODO: 14/10/2017 Improve this method using lambdas and streams
     * @ TODO: 14/10/2017 Improve this code by reducing the receptiveness of the loops
     *
     * @return If someone has won the game
     */
    boolean hasWinner() {
        Piece piece = tiles[lastMove.x][lastMove.y];
        int side1 = 0, side2 = 0;

        //Check for left pieces
        for (int i = lastMove.x; i >= 0; i--) {
            if (tiles[i][lastMove.y] == piece)
                side1++;
        }

        //Check for right pieces
        for (int i = lastMove.x; i < DIMENSIONS; i++) {
            if (tiles[i][lastMove.y] == piece)
                side2++;
        }

        //Sum the two and take one away (because we counted the middle piece twice)
        //and check if that spans the whole board
        if ((side1 + side2 - 1) == DIMENSIONS)
            return true;

        //Return sides to zero
        side1 = 0;
        side2 = 0;

        //Check for up pieces
        for (int i = lastMove.y; i >= 0; i--) {
            if (tiles[lastMove.x][i] == piece)
                side1++;
        }

        //Check for down pieces
        for (int i = lastMove.y; i < DIMENSIONS; i++) {
            if (tiles[lastMove.x][i] == piece)
                side2++;
        }

        //Sum the two and take one away (because we counted the middle piece twice)
        //and check if that spans the whole board
        if ((side1 + side2 - 1) == DIMENSIONS)
            return true;

        //Return sides to zero
        side1 = 0;
        side2 = 0;

        //Check for diagonal up-right
        for (int i = lastMove.x; i < DIMENSIONS; i++) {
            for (int j = lastMove.y; j < DIMENSIONS; j++) {
                if (tiles[i][j] == piece)
                    side1++;
            }
        }

        //Check for diagonal down-left
        for (int i = lastMove.x; i >= 0; i--) {
            for (int j = lastMove.y; j >= 0; j--) {
                if (tiles[i][j] == piece)
                    side2++;
            }
        }

        //Check to see if diagonal wins
        if ((side1 + side2 - 1) == DIMENSIONS)
            return true;

        //Return sides to zero
        side1 = 0;
        side2 = 0;

        //Check for diagonal up-left
        for (int i = lastMove.x; i < DIMENSIONS; i++) {
            for (int j = lastMove.y; j >= 0; j--) {
                if (tiles[i][j] == piece)
                    side1++;
            }
        }

        //Check for diagonal down-right
        for (int i = lastMove.x; i >= 0; i--) {
            for (int j = lastMove.y; j < DIMENSIONS; j++) {
                if (tiles[i][j] == piece)
                    side2++;
            }
        }

        //Check to see if diagonal wins
        return (side1 + side2 - 1) == DIMENSIONS;
    }
}
