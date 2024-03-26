package chess.domain.piece;

public enum PieceColor {

    BLACK,
    WHITE;

    public PieceColor opposite() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isSame(final String color) {
        return name().equals(color);
    }
}
