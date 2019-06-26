package chess.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {
    private static final int MIN = 0;
    private static final int MAX = 7;
    private static final int SQUARE_ROOT = 2;

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

    public static Position from(String position) {
        String[] numbers = position.split(",");

        return Position.of(Integer.parseInt(numbers[1]), Integer.parseInt(numbers[0]));
    }

    public boolean isSameColumn(Position position) {
        return this.x == position.x;
    }

    public int calculateColumnDistance(Position position) {
        return this.x - position.x;
    }

    public int calculateRowDistance(Position position) {
        return this.y - position.y;
    }

    public double getDistance(Position target) {
        double calculationX = Math.pow(this.calculateColumnDistance(target), SQUARE_ROOT);
        double calculationY = Math.pow(this.calculateRowDistance(target), SQUARE_ROOT);

        return Math.sqrt(calculationX + calculationY);
    }

    public int isInLine(Position target) {
        if (this.x == target.x) {
            return 0;
        }

        if (this.y == target.y) {
            return 1;
        }

        return -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
