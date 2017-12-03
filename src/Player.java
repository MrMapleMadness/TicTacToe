class Player {

    private Piece piece;
    private String name;
    private int score;

    /**
     * The player that places pieces down.
     *
     * @author Angus Clinch
     * @param piece The piece that this player will use during the game
     * @param name  The name of the player
     */
    Player(Piece piece, String name) {
        this.piece = piece;
        this.name = name;
        score = 0;
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
     *
     * @return The name of the player
     */
    String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The name of the player to set
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Simple method that increments the players score when they win a game.
     */
    void winsGame(){
        score++;
    }

    /**
     * Gets the score of the player.
     * @return The players score
     */
    int getScore() {
        return score;
    }
}
