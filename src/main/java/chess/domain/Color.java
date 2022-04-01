package chess.domain;

public enum Color {
    WHITE,
    BLACK;

    public Color opponentColor() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
