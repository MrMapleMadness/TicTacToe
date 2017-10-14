class Player {

    private Piece piece;
    private String name;

    Player(Piece piece, String name) {
        this.piece = piece;
        this.name = name;
    }

    Piece getPiece() {
        return piece;
    }

    String getName() {
        return name;
    }
}
