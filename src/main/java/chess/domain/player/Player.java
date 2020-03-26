package chess.domain.player;

public enum Player {

    WHITE,
    BLACK;

    public Player toggle() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

}
