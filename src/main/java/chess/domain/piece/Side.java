package chess.domain.piece;

public enum Side {

    BLACK,
    WHITE,
    NEUTRALITY;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isNeutrality() {
        return this == NEUTRALITY;
    }

    public static Side calculateWinner(double whiteScore, double blackScore) {
        if (whiteScore > blackScore) {
            return WHITE;
        }
        if (whiteScore < blackScore) {
            return BLACK;
        }
        return NEUTRALITY;
    }
}
