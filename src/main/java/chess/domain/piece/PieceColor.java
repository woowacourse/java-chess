package chess.domain.piece;

public enum PieceColor {

    BLACK,
    WHITE,
    NONE,
    ;

    public PieceColor next() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
