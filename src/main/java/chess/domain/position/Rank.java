package chess.domain.position;

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

    private static final String WRONG_RANK_POSITION = "올바르지 않은 rank 위치 정보입니다.";

    private final String rank;
    private final int rowIndex;

    Rank(String rank, int rowIndex) {
        this.rank = rank;
        this.rowIndex = rowIndex;
    }

    public static int findRow(String column) {
        Rank rank = Arrays.stream(values())
                .filter(value -> value.rank.equals(column))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_RANK_POSITION));
        return rank.rowIndex;
    }
}
