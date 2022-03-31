package chess.model.strategy.move;

import java.util.HashMap;
import java.util.Map;

public class Distance {
    private static final int MINIMUM_DISTANCE = 1;
    private static Map<Integer, Distance> distances = new HashMap<>();

    private final int value;

    private Distance(int value) {
        if (value < MINIMUM_DISTANCE) {
            throw new IllegalArgumentException("이동 거리는 최소 1 이상이어야 합니다.");
        }
        this.value = value;
    }

    public static Distance of(int value) {
        if (!distances.containsKey(value)) {
            distances.put(value, new Distance(value));
        }
        return distances.get(value);
    }

    public boolean isInRange(int moveLimit) {
        return value <= moveLimit;
    }
}
