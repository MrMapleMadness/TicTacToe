class Player {

    private Piece piece;
    private String name;

    /**
     * The player that places pieces down.
     *
     * @ TODO: 14/10/2017 Make players more robust so that players can have custom names
     *
     * @param piece The piece that this player will use during the game
     * @param name The name of the player
     */
    Player(Piece piece, String name) {
        this.piece = piece;
        this.name = name;
    }

    /**
     * Returns the type of piece that the player is using.
     *
     * @return The type of piece that the player is using.
     */
    Piece getPiece() {
        return piece;
    }

    /**
     * Returns the name of the player.
     * @return The name of the player
     */
    String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     * @param name The name of the player to set
     */
    void setName(String name) {
        this.name = name;
    }
}
