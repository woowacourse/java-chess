package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK,
    NONE;
    
    public Color reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }
}
