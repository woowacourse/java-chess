package chess.domain.board.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Rank {

    EIGHT("8", 7),
    SEVEN("7", 6),
    SIX("6", 5),
    FIVE("5", 4),
    FOUR("4", 3),
    THREE("3", 2),
    TWO("2", 1),
    ONE("1", 0);

    private static final String INVALID_IDX_RANGE_EXCEPTION_MESSAGE = "존재하지 않는 열입니다. (범위: 1~8)";

    private final String key;
    private final int value;

    Rank(String key, int value) {
        this.key = key;
        this.value = value;
    }

    static Rank of(String key) {
        return Arrays.stream(values())
                .filter(rank -> Objects.equals(rank.key, key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_IDX_RANGE_EXCEPTION_MESSAGE));
    }

    public static List<Rank> allRanksDescending() {
        return List.of(values());
    }

    int valueDifference(Rank to) {
        return to.value - value;
    }

    Rank oneRankToward(Rank targetRank) {
        int incrementedIdx = incrementToward(targetRank);
        return Arrays.stream(values())
                .filter(rank -> rank.value == incrementedIdx)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_IDX_RANGE_EXCEPTION_MESSAGE));
    }

    private int incrementToward(Rank targetRank) {
        int from = this.value;
        int to = targetRank.value;
        return from + Integer.compare(to, from);
    }

    String key() {
        return key;
    }

    @Override
    public String toString() {
        return "Rank{" + "key='" + key + '\'' + ", value=" + value + '}';
    }
}
