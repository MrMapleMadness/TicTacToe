public enum Piece {
    BLANK (" "),
    X ("X"),
    O ("O");

    private final String pieceCode;

    Piece(String pieceCode){
        this.pieceCode = pieceCode;
    }

    public String getPieceCode() {
        return pieceCode;
    }
}
