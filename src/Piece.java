import javafx.scene.image.Image;

public enum Piece {
    BLANK(" "),
    X("X"),
    O("O");

    private final String pieceCode;

    /**
     * Constructs a piece.
     *
     * @param pieceCode The real wold representation of the piece
     * @author Angus Clinch
     */
    Piece(String pieceCode) {
        this.pieceCode = pieceCode;
    }

    /**
     * Returns the real world representation of the piece.
     *
     * @return The real world representation of the piece
     */
    public String getPieceCode() {
        return pieceCode;
    }

    /**
     * Returns the image that should be used for each piece.
     *
     * @return The image that should be used for each piece
     * @see Image
     */
    public Image getImage() {

        //Image Locations - change depending on the image displayed
        final String xImage = "img/spire.png";
        final String oImage = "img/eCase.png";
        final String bImage = "img/blank.png";

        switch (getPieceCode()) {
            case "X":
                return new Image(xImage);
            case "O":
                return new Image(oImage);
            default:
                return new Image(bImage);
        }
    }

    /**
     * Checks if two pieces are equivalent.
     *
     * @param piece The piece to compare
     * @return If the pieces are equivalent
     */
    public boolean equals(Piece piece) {
        return getPieceCode().equals(piece.getPieceCode());
    }
}
