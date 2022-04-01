package chess.domain.position;

import java.util.stream.Stream;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private static final int WHITE_PAWN_FIRST_RANK = 2;
    private static final int BLACK_PAWN_FIRST_RANK = 7;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank from(final int value) {
        return Stream.of(Rank.values())
                .filter(rank -> rank.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 rank 범위 입니다."));
    }

    public boolean isFirstTurnOfPawn() {
        return value == WHITE_PAWN_FIRST_RANK || value == BLACK_PAWN_FIRST_RANK;
    }

    public int calculateRank(Rank otherRank) {
        return value - otherRank.getValue();
    }
    public int calculateRankInAbsolute(Rank otherRank) {
        return Math.abs(value - otherRank.getValue());
    }

    public int getValue() {
        return value;
    }
}
