package chess.model.piece;

public enum Side {
    BLACK,
    WHITE,
    NONE;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
