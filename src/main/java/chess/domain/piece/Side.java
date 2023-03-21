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
}
