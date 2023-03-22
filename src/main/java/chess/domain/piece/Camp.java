package chess.domain.piece;

public enum Camp {
    BLACK,
    WHITE,
    ;

    public Camp convert() {
        if (this == BLACK) {
            return WHITE;
        }

        return BLACK;
    }
}
