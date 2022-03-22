package domain.piece;

public class Piece {

    private final Player player;

    public Piece(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
