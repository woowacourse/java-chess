package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Counter<T> {
    private final Map<T, Integer> counter;

    private Counter() {
        counter = new HashMap<>();
    }

    public static Counter create() {
        return new Counter();
    }

    public void increase(T key) {
        counter.put(key, count(key) + 1);
    }

    public int count(T key) {
        if (counter.containsKey(key)) {
            return counter.get(key);
        }
        return 0;
    }

    public Set<T> keySet() {
        return counter.keySet();
    }
}
