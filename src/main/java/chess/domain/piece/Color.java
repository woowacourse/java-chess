package chess.domain.piece;

public enum Color {

    WHITE,
    BLACK;

    public Color getCounter() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
