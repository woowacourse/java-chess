package chess.domain.piece;

public enum TeamColor {
    BLACK,
    WHITE;

    public static TeamColor divide(final char columnPosition) {
        if (Character.isLowerCase(columnPosition)) {
            return WHITE;
        }
        return BLACK;
    }

    public TeamColor changeTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
