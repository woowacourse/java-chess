package chess.domain;

public enum Color {
    BLACK,
    WHITE,
    EMPTY;

    public static Color of(RankCoordinate rankCoordinate) {
        if (rankCoordinate.isWhiteRank()) {
            return Color.WHITE;
        }
        if (rankCoordinate.isBlackRank()) {
            return Color.BLACK;
        }
        return Color.EMPTY;
    }
}
