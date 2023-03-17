package chessgame.domain.point;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private static final Map<Integer, Rank> RANK_MAP = Collections.unmodifiableMap(Stream.of(values()).collect(
        Collectors.toMap(Rank::getValue, Function.identity())));
    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank findRank(int value) {
        if (RANK_MAP.containsKey(value)) {
            return RANK_MAP.get(value);
        }
        throw new IllegalArgumentException("rank 좌표가 잘못되었습니다.");
    }

    public int distance(Rank target) {
        return this.value - target.value;
    }

    public Rank move(int rankMove) {
        int result = value + rankMove;

        return findRank(result);
    }

    private int getValue() {
        return value;
    }
}
