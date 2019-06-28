package chess.domain.piece;

public enum PieceColor {
    WHITE, BLACK;

    public PieceColor opposite() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
