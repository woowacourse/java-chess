package chess.model.strategy.move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Distance {
    private static Map<Integer, Distance> distances = new HashMap<>();

    private final int value;

    private Distance(int value) {
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
