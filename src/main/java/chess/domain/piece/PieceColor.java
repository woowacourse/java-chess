package chess.domain.piece;

public enum PieceColor {

    BLACK,
    WHITE;

    public PieceColor next() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
