package chess.domain.square;

import java.util.Arrays;

public enum Rank {
    EIGHT("8", 0),
    SEVEN("7", 1),
    SIX("6", 2),
    FIVE("5", 3),
    FOUR("4", 4),
    THREE("3", 5),
    TWO("2", 6),
    ONE("1", 7);

    private final String rank;
    private final int index;

    Rank(final String rank, final int index) {
        this.rank = rank;
        this.index = index;
    }

    public static int calculate(final Rank src, final Rank dst) {
        return src.index - dst.index;
    }

    public Rank next(final int direction) {
        return findRankBy(this.index + direction);
    }

    public static Rank findRankBy(final String rankInput) {
        return Arrays.stream(Rank.values())
                .filter(value -> value.rank.equals(rankInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Rank를 입력했습니다."));
    }

    private Rank findRankBy(final int rankIndex) {
        return Arrays.stream(Rank.values())
                .filter(value -> value.index == rankIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Rank Index를 입력했습니다."));
    }
}
