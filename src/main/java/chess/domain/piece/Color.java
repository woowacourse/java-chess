package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE;

    public Color getOtherColor() {
        if (this.equals(BLACK)) {
            return WHITE;
        }

        return BLACK;
    }
}
