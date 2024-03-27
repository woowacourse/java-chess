package chess.domain.board;

import java.util.Map;
import java.util.Map.Entry;

public enum Weight {

    ONE_LEFT(Map.entry(-1, 0)),
    ONE_RIGHT(Map.entry(1, 0)),
    ONE_UP(Map.entry(0, 1)),
    ONE_DOWN(Map.entry(0, -1)),
    TWO_UP(Map.entry(0, 2)),
    TWO_DOWN(Map.entry(0, -2)),
    ONE_LEFT_ONE_DOWN(Map.entry(-1, -1)),
    ONE_LEFT_ONE_UP(Map.entry(-1, 1)),
    ONE_RIGHT_ONE_DOWN(Map.entry(1, -1)),
    ONE_RIGHT_ONE_UP(Map.entry(1, 1)),
    ONE_LEFT_TWO_UP(Map.entry(-1, 2)),
    ONE_LEFT_TWO_DOWN(Map.entry(-1, -2)),
    TWO_LEFT_ONE_DOWN(Map.entry(-2, -1)),
    TWO_LEFT_ONE_UP(Map.entry(-2, 1)),
    ONE_RIGHT_TWO_DOWN(Map.entry(1, -2)),
    ONE_RIGHT_TWO_UP(Map.entry(1, 2)),
    TWO_RIGHT_ONE_DOWN(Map.entry(2, -1)),
    TWO_RIGHT_ONE_UP(Map.entry(2, 1)),

    ;
    private final Map.Entry<Integer, Integer> value;

    Weight(final Entry<Integer, Integer> value) {
        this.value = value;
    }

    public Entry<Integer, Integer> value() {
        return value;
    }
}
