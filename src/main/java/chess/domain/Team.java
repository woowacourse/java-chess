package chess.domain;

public enum Team {
    WHITE,
    BLACK;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
