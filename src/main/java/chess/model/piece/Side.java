package chess.model.piece;

public enum Side {
    BLACK,
    WHITE,
    EMPTY;

    public boolean isWhite() {
        return WHITE.equals(this);
    }

    public boolean isBlack() {
        return BLACK.equals(this);
    }

    public boolean isEmpty() {
        return EMPTY.equals(this);
    }
}
