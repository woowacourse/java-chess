package domain.piece.info;

import java.util.Arrays;
import java.util.List;

public enum Rank {

    EIGHT(7),
    SEVEN(6),
    SIX(5),
    FIVE(4),
    FOUR(3),
    THREE(2),
    TWO(1),
    ONE(0);

    public static final String RANK_NOT_FOUND = "랭크가 잘못되었습니다.";
    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank of(final int rankNumber) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == rankNumber)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(RANK_NOT_FOUND));
    }

    public int index() {
        return index;
    }

    public static List<Rank> whitePawnRank() {
        return List.of(TWO);
    }

    public static List<Rank> whiteOtherRank() {
        return List.of(ONE);
    }

    public static List<Rank> blackPawnRank() {
        return List.of(SEVEN);
    }

    public static List<Rank> blackOtherRank() {
        return List.of(EIGHT);
    }

    public static List<Rank> nonePosition() {
        return List.of(THREE, FOUR, FIVE, SIX);
    }
}
