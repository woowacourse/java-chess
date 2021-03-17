package chess.domain.piece;

public enum Color {
    BLACK, WHITE, NO_COLOR;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
