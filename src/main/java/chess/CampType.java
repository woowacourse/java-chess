package chess;

public enum CampType {
    BLACK,
    WHITE;

    public static CampType divide(final char columnPosition) {
        if (Character.isLowerCase(columnPosition)) {
            return WHITE;
        }
        return BLACK;
    }
}
