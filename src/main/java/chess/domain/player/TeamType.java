package chess.domain.player;

public enum TeamType {
    WHITE,
    BLACK;

    public TeamType nextTurn() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
