package chess.domain.piece;

public enum Color {

    WHITE, BLACK, NO_COLOR;

    public static Color oppose(Color previousColor) {
        if (previousColor == WHITE) {
            return BLACK;
        }
        if (previousColor == BLACK) {
            return WHITE;
        }
        throw new IllegalArgumentException("NO_COLOR는 반전시킬 수 없습니다.");
    }
}
