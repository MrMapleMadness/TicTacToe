import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

class Board {

    private Piece[][] tiles;
    private static final int DIMENSIONS = 3;
    private Point lastMove;

    Board() {
        tiles = new Piece[DIMENSIONS][DIMENSIONS];
        generateCleanBoard();
    }

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

    private boolean checkValid(int row, int col) {
        return (row >= 0 && row < DIMENSIONS) && (col >= 0 && col < DIMENSIONS)
                && tiles[row][col] == Piece.BLANK;
    }

    void setTile(Point point, Piece piece) {
        tiles[point.x][point.y] = piece;
    }

    private void generateCleanBoard() {
        for (int i = 0; i < DIMENSIONS; i++) {
            for (int j = 0; j < DIMENSIONS; j++) {
                tiles[i][j] = Piece.BLANK;
            }
        }
    }

    void printBoard() {
        System.out.println("-------------");
        System.out.println("| " + tiles[0][0].getPieceCode() + " | " + tiles[0][1].getPieceCode() + " | " + tiles[0][2].getPieceCode() + " |");
        System.out.println("|---|---|---|");
        System.out.println("| " + tiles[1][0].getPieceCode() + " | " + tiles[1][1].getPieceCode() + " | " + tiles[1][2].getPieceCode() + " |");
        System.out.println("|---|---|---|");
        System.out.println("| " + tiles[2][0].getPieceCode() + " | " + tiles[2][1].getPieceCode() + " | " + tiles[2][2].getPieceCode() + " |");
        System.out.println("-------------");
    }


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
        if ((side1 + side2 - 1) == 3)
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
        return (side1 + side2 - 1) == 3;
    }
}
