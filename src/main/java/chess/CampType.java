package chess;

public enum CampType {
    UPPER,
    LOWER;

    public static CampType divide(final char columnPosition) {
        if (Character.isLowerCase(columnPosition)) {
            return LOWER;
        }
        return UPPER;
    }
}
