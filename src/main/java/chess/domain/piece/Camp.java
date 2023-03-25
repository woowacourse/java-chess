package chess.domain.piece;

public enum Camp {
    BLACK,
    WHITE,
    NONE;

    public boolean isOpposite(final Camp otherCamp) {
        if (otherCamp == NONE) {
            return false;
        }

        return this != otherCamp;
    }

    public Camp getOpposite() {
        if (this == BLACK) {
            return WHITE;
        }

        if (this == WHITE) {
            return BLACK;
        }

        return NONE;
    }
}
