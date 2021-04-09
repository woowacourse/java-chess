package chess.domain.piece;

public enum Color {
    WHITE, BLACK;

    public static Color of(char name) {
        if (Character.isLowerCase(name)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
