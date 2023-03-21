package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK;

    public boolean isWhite() {
        return this == WHITE;
    }
}
