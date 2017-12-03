import java.awt.*;
import java.util.Arrays;

/**
 * Defines a board that is used in the game Tic Tac Toe, this can be a 3 x 3 grid or bigger.
 *
 * @author Angus Clinch
 * @see <a href="https://www.wikiwand.com/en/Tic-tac-toe">Wikepedia article about Tic Tac Toe</a>
 */
class Board {

    //GUI
    private Output output;
    //Logic
    private static final int DIMENSIONS = 3;
    private Piece[][] tiles;
    private Point lastMove;

    /**
     * Constructor for this class. It creates a new two dimensional array
     * to store the tile data and generates a clean board.
     */
    Board(Output output) {
        this.output = output;
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
        Point point = new Point();
        while (!done) {
            point = output.getInput(DIMENSIONS);
            done = checkValid(point);
            if (!done)
                output.displayMessage("Sorry those dimensions aren't in the board");
        }

        lastMove = point;
        return point;
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
     * Checks the validity of the players move.
     *
     * @param point The point to check the validity of
     * @return If the placement of the piece is valid
     */
    private boolean checkValid(Point point) {
        return point != null && checkValid(point.x, point.y);
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
        output.printBoard(tiles);
    }

    /**
     * Checks to see if there are any winning moves on the board. Essentially
     * all these checks will do is check some directional array of pieces for
     * equality using a stream.
     *
     * @return If someone has won the game
     */
    boolean hasWinner() {
        Piece piece = tiles[lastMove.x][lastMove.y];
        return Arrays.stream(tiles[lastMove.x]).allMatch(piece::equals)
                || Arrays.stream(getTileColumn(lastMove.y)).allMatch(piece::equals)
                || Arrays.stream(getDiagonalOne()).allMatch(piece::equals)
                || Arrays.stream(getDiagonalTwo()).allMatch(piece::equals);
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
    private Piece[] getDiagonalOne() {
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
    private Piece[] getDiagonalTwo() {
        Piece[] temp = new Piece[DIMENSIONS];
        int j = 0;
        for (int i = DIMENSIONS - 1; i > 0; i--) {
            temp[j] = tiles[j][i];
            j++;
        }
        return temp;
    }
}