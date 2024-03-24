package chess.domain.piece;

public enum PieceColor {
    WHITE,
    BLACK;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public PieceColor reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
