package chess.domain;

import java.util.*;
import java.util.function.Consumer;

public class CoordinatePair {
    private final CoordinateX x;
    private final CoordinateY y;

    private static Map<String, CoordinatePair> coordinateMap = new HashMap<>();

    static {
        for (CoordinateX x : CoordinateX.values()) {
            for (CoordinateY y : CoordinateY.values()) {
                coordinateMap.put(x.getSymbol() + y.getSymbol(), new CoordinatePair(x, y));
            }
        }
    }

    private CoordinatePair(CoordinateX x, CoordinateY y) {
        this.x = x;
        this.y = y;
    }

    public CoordinateX getX() {
        return x;
    }

    public CoordinateY getY() {
        return y;
    }

    public static Optional<CoordinatePair> from(String symbol) {
        return Optional.ofNullable(coordinateMap.get(symbol));
    }

    public static CoordinatePair of(CoordinateX x, CoordinateY y) {
        return from(x.getSymbol() + y.getSymbol())
            .orElseThrow(() -> new NoSuchElementException("좌표가 캐시에 없습니다: " + x + ", " + y));
    }

    public static void forEachCoordinate(Consumer<CoordinatePair> consumer) {
        List<CoordinateX> xAxis = CoordinateX.allAscendingCoordinates();
        List<CoordinateY> yAxis = CoordinateY.allAscendingCoordinates();
        yAxis.forEach(y ->
            xAxis.forEach(x -> consumer.accept(of(x, y))));
    }

    @Override
    public String toString() {
        return String.format("Coordinate { x: %s, y: %s }", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatePair that = (CoordinatePair) o;
        return x == that.x &&
            y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
