package chess.domain;

public enum Color {
    BLACK,
    WHITE,
    EMPTY;

    public static Color of(RankCoordinate rankCoordinate) {
        if (rankCoordinate == RankCoordinate.ONE || rankCoordinate == RankCoordinate.TWO) {
            return Color.WHITE;
        }
        if (rankCoordinate == RankCoordinate.EIGHT || rankCoordinate == RankCoordinate.SEVEN) {
            return Color.BLACK;
        }
        return Color.EMPTY;
    }
}
