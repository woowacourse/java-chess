package domain.piece;

public enum Side {
    BLACK,
    WHITE,
    NEUTRAL;

    public static Side calculateWinner(double whiteScore, double blackScore) {
        if (whiteScore == blackScore) {
            return Side.NEUTRAL;
        }
        if (whiteScore > blackScore) {
            return Side.WHITE;
        }

        return Side.BLACK;
    }

    public Side nextSide() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
