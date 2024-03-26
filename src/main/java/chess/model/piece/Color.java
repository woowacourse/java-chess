package chess.model.piece;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    public static Color findColorByName(String pieceName) {
        if (pieceName.equals(".")) {
            return NONE;
        }
        if (Character.isUpperCase(pieceName.charAt(0))) {
            return BLACK;
        }
        return WHITE;
    }

    public Color changeColor() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }

    public boolean isSameColor(Color color) {
        return this == color;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
