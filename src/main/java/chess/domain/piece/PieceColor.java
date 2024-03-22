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
}
