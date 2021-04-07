package chess.domain.piece;

public enum Color {
    WHITE, BLACK;

    public static Color findColorByTurn(boolean isBlackTurn) {
        if (isBlackTurn) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }
}
