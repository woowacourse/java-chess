package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE;

    public Color getOpposite() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
