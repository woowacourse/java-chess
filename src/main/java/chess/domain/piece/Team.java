package chess.domain.piece;

public enum Team {

    BLACK,
    WHITE;

    public Team getNextTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
