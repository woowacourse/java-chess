package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK,
    NONE;
    
    public static Color reverse(Color color) {
        if (color == WHITE) {
            return BLACK;
        }
        if (color == BLACK) {
            return WHITE;
        }
        return NONE;
    }
}
