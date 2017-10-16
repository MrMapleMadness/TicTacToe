public enum Piece {
    BLANK(" "),
    X("X"),
    O("O");

    private final String pieceCode;

    /**
     * Constructs a piece.
     *
     * @param pieceCode The real wold representation of the piece
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
     * Checks if two pieces are equivalent.
     *
     * @param piece The piece to compare
     * @return If the pieces are equivalent
     */
    public boolean equals(Piece piece) {
        return getPieceCode().equals(piece.getPieceCode());
    }
}
