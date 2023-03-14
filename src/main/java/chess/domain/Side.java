package chess.domain;

public enum Side {

    BLACK,
    WHITE;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
