package chess.domain.attribute;

public enum Rank {

    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT
    ;

    public static Rank startRankOf(final Color color) {
        if (color == Color.WHITE) {
            return ONE;
        }
        return EIGHT;
    }

    public static Rank startPawnRankOf(final Color color) {
        if (color == Color.WHITE) {
            return TWO;
        }
        return SEVEN;
    }
}
