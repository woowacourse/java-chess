package chess.domain.pieceInfo;

import java.util.Arrays;

public enum Rank {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private final String rank;
    private final int index;

    Rank(final String rank, final int index) {
        this.rank = rank;
        this.index = index;
    }

    public static Rank valueByRank(final String rank) {
        return Arrays.stream(values())
                .filter(value -> value.rank.equals(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("보드의 범위를 벗어난 좌표입니다."));
    }

    public static Rank valueByIndex(final int index) {
        return Arrays.stream(values())
                .filter(value -> value.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("보드의 범위를 벗어난 좌표입니다."));
    }

    public int getIndex() {
        return index;
    }

}
