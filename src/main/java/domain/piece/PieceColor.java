package domain.piece;

public enum PieceColor {
    BLACK, WHITE;

    public PieceColor toggle() {
        if (this == BLACK) {
            return WHITE;
        }

        return BLACK;
    }
}
