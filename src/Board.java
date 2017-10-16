import java.awt.*;
import java.util.Arrays;
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
     * @return A valid point on the board
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
     * <p>
     * @ TODO: 14/10/2017 Improve generateCleanBoard using streams
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
     * @ TODO: 14/10/2017 Make printBoard a graphical interface
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
     * Checks to see if there are any winning moves on the board. Essentially
     * all these checks will do is check some directional array of pieces for
     * equality using a stream.
     *
     * @return If someone has won the game
     *
     * @ TODO: 16/10/2017 Make hasWinner one return statement
     *          This is a difficult decision, I like to do things in one line, I find
     *          that code done in one displays a level of polish that a lot of programmers
     *          don't care about. As Blaise Pascal said - "If I had more time I would have
     *          written a shorter letter."
     */
    boolean hasWinner() {
        Piece piece = tiles[lastMove.x][lastMove.y];

        if (Arrays.stream(tiles[lastMove.x]).allMatch(piece::equals))
            return true;

        if (Arrays.stream(getTileColumn(lastMove.y)).allMatch(piece::equals))
            return true;

        if (Arrays.stream(getDiagonalOne()).allMatch(piece::equals))
            return true;

        return Arrays.stream(getDiagonalTwo()).allMatch(piece::equals);
    }

    /**
     * Helper method to get the pieces in one column of an array.
     *
     * @param col The column of interest
     * @return An array containing the column of pieces.
     */
    private Piece[] getTileColumn(int col) {
        Piece[] temp = new Piece[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            temp[i] = tiles[i][col];
        }
        return temp;
    }

    /**
     * Returns an array that contains the diagonal pieces from
     * the top left to the bottom right of the tiles array.
     *
     * @return An array from tiles containing the top left to bottom right
     * diagonal pieces
     */
    private Piece[] getDiagonalOne(){
        Piece[] temp = new Piece[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            temp[i] = tiles[i][i];
        }
        return temp;
    }

    /**
     * Returns an array that contains the diagonal pieces from
     * the top right to the bottom left of the tiles array.
     *
     * @return An array from tiles containing the top right to bottom left
     * diagonal pieces
     */
    private Piece[] getDiagonalTwo(){
        Piece[] temp = new Piece[DIMENSIONS];
        int j = 0;
        for (int i = DIMENSIONS - 1; i > 0; i--) {
            temp[j] = tiles[j][i];
            j++;
        }
        return temp;
    }
}
