package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE,
    EMPTY,
    ;

    public boolean isEnemy(final Team other) {
        if (this == BLACK) {
            return other == WHITE;
        }
        if (this == WHITE) {
            return other == BLACK;
        }
        return false;
    }
}
