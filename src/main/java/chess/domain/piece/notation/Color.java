package chess.domain.piece.notation;

public enum Color {
    BLACK, WHITE, EMPTY;

    public boolean isWhite() {
        return this == WHITE;
    }

    public Color next() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
