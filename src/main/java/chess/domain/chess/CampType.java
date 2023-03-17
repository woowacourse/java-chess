package chess.domain.chess;

public enum CampType {
    BLACK,
    WHITE;

    public static CampType divide(final char columnPosition) {
        if (Character.isLowerCase(columnPosition)) {
            return WHITE;
        }
        return BLACK;
    }

    public CampType changeTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
