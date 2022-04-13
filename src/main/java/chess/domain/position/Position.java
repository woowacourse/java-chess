package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {

    private final XAxis xAxis;
    private final YAxis yAxis;

    private Position(XAxis xAxis, YAxis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public static Position of(XAxis xAxis, YAxis yAxis) {
        return Cache.findByXAxisAndYAxis(xAxis, yAxis);
    }

    public static Position of(int xAxisValue, int yAxisValue) {
        XAxis xAxis = XAxis.getByValue(xAxisValue);
        YAxis yAxis = YAxis.getByValue(yAxisValue);

        return of(xAxis, yAxis);
    }

    public static Position of(String xAxisValue, String yAxisValue) {
        XAxis xAxis = XAxis.getByValue(xAxisValue);
        YAxis yAxis = YAxis.getByValue(yAxisValue);

        return of(xAxis, yAxis);
    }

    // TODO: 리팩토링
    public static Position from(String coordinate) {
        XAxis xAxis = XAxis.valueOf(coordinate.substring(0, 1).toUpperCase(Locale.ROOT));
        YAxis yAxis = YAxis.getByValue(Integer.parseInt(coordinate.substring(1, 2)));

        return of(xAxis, yAxis);
    }

    public static List<Position> getPositionsByXAxis(XAxis xAxis) {
        return Arrays.stream(YAxis.values())
                .map(yAxis -> Position.of(xAxis, yAxis))
                .collect(Collectors.toList());
    }

    public static double calculateDegree(Position position1, Position position2) {
        int deltaX = position2.xAxis.getValue() - position1.xAxis.getValue();
        int deltaY = position2.yAxis.getValue() - position1.yAxis.getValue();

        return Math.toDegrees(Math.atan2(deltaX, deltaY));
    }

    public static List<Position> getAllPositions() {
        return List.copyOf(Cache.cache);
    }

    public boolean hasSameXAxisAs(Position other) {
        return this.xAxis.equals(other.xAxis);
    }

    public boolean hasSameYAxisAs(Position other) {
        return this.yAxis.equals(other.yAxis);
    }

    public boolean isSameYAxis(YAxis yAxis) {
        return this.yAxis == yAxis;
    }

    public int subtractXAxis(Position other) {
        return this.xAxis.subtract(other.xAxis);
    }

    public int subtractYAxis(Position other) {
        return this.yAxis.subtract(other.yAxis);
    }

    public boolean isVerticalDistanceShorterThan(Position other, int range) {
        return Math.abs(this.yAxis.subtract(other.yAxis)) <= range;
    }

    public boolean isFartherThanOneStep(Position other) {
        int xAxisDelta = Math.abs(other.xAxis.getValue() - this.xAxis.getValue());
        int yAxisDelta = Math.abs(other.yAxis.getValue() - this.yAxis.getValue());

        return xAxisDelta > 1 || yAxisDelta > 1;
    }

    public List<Position> getPositionsSameYAxisBetween(Position other) {
        return YAxis.getBetween(this.yAxis, other.yAxis).stream()
                .map(yAxis -> Position.of(this.xAxis, yAxis))
                .collect(Collectors.toList());
    }

    public List<Position> getPositionsSameXAxisBetween(Position other) {
        return XAxis.getBetween(this.xAxis, other.xAxis).stream()
                .map(xAxis -> Position.of(xAxis, this.yAxis))
                .collect(Collectors.toList());
    }

    // TODO: 리팩토링
    public List<Position> getPositionsSameDirectionDiagonalBetween(Position to) {
        int xAxisDelta = xAxis.getValue() - to.xAxis.getValue();
        int yAxisDelta = yAxis.getValue() - to.yAxis.getValue();
        int time = Math.abs(xAxisDelta);

        int xDirection = -(xAxisDelta / time);
        int yDirection = -(yAxisDelta / time);

        return IntStream.range(1, time)
                .mapToObj(idx -> getPositionWith(xDirection, yDirection, idx))
                .collect(Collectors.toList());
    }

    private Position getPositionWith(int xDirection, int yDirection, int idx) {
        XAxis xAxis1 = XAxis.getByValue(this.xAxis.getValue() + xDirection * idx);
        YAxis yAxis1 = YAxis.getByValue(this.yAxis.getValue() + yDirection * idx);

        return Position.of(xAxis1, yAxis1);
    }

    public String toCoordinate() {
        return xAxis.name().toLowerCase(Locale.ROOT) + yAxis.getValue();
    }

    @Override
    public String toString() {
        return "Position{" +
                "XAxis=" + xAxis +
                ", YAxis=" + yAxis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;

        if (xAxis != position.xAxis) {
            return false;
        }
        return yAxis == position.yAxis;
    }

    public XAxis getXAxis() {
        return xAxis;
    }

    public YAxis getYAxis() {
        return yAxis;
    }

    @Override
    public int hashCode() {
        int result = xAxis != null ? xAxis.hashCode() : 0;
        result = 31 * result + (yAxis != null ? yAxis.hashCode() : 0);
        return result;
    }

    private static class Cache {

        private static final List<Position> cache;

        static {
            cache = Arrays.stream(XAxis.values()).
                    flatMap(xAxis -> Arrays.stream(YAxis.values())
                            .map(yAxis -> new Position(xAxis, yAxis)))
                    .collect(Collectors.toList());
        }

        public static Position findByXAxisAndYAxis(XAxis xAxis, YAxis yAxis) {
            return cache.stream()
                    .filter(position -> position.xAxis.equals(xAxis))
                    .filter(position -> position.yAxis.equals(yAxis))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("좌표가 존재하지 않습니다."));
        }
    }
}
