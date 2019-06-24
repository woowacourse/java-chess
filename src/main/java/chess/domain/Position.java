package chess.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {
    private static final int MIN = 0;
    private static final int MAX = 7;

    private final int x;
    private final int y;
    private static Map<Integer, Map<Integer, Position>> cacheMap;

    private Position(int y, int x) {
        this.x = x;
        this.y = y;
    }

    static {
        cacheMap = IntStream.rangeClosed(MIN, MAX)
                .boxed()
                .collect(Collectors
                        .toMap(Function.identity(), y -> IntStream.rangeClosed(MIN, MAX)
                                .boxed()
                                .collect(Collectors.toMap(Function.identity(), x -> new Position(y, x)))));
    }

    public static Position of(int y, int x) {
        return cacheMap.get(y).get(x);
    }
}
