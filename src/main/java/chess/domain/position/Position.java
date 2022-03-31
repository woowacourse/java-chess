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

    public static Position from(XAxis xAxis, YAxis yAxis) {
        return Cache.cache.stream()
                .filter(position -> position.xAxis == xAxis && position.yAxis == yAxis)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("좌표가 존재하지 않습니다."));
    }

    public static Position from(String coordinate) {
        XAxis xAxis = XAxis.valueOf(coordinate.substring(0, 1).toUpperCase(Locale.ROOT));
        YAxis yAxis = YAxis.getByValue(Integer.parseInt(coordinate.substring(1, 2)));

        return from(xAxis, yAxis);
    }

    public static List<Position> getPositionsByXAxis(XAxis xAxis) {
        return Arrays.stream(YAxis.values())
                .map(yAxis -> Position.from(xAxis, yAxis))
                .collect(Collectors.toList());
    }

    public static double calculateDegree(Position position1, Position position2) {
        int deltaX = position2.xAxis.getValue() - position1.xAxis.getValue();
        int deltaY = position2.yAxis.getValue() - position1.yAxis.getValue();

        return Math.toDegrees(Math.atan2(deltaX, deltaY));
    }

    public boolean isSameXAxis(Position other) {
        return this.xAxis.equals(other.xAxis);
    }

    public boolean isSameYAxis(Position other) {
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

    public boolean isOnDiagonal(Position other) {
        int xAxisDelta = Math.abs(other.xAxis.getValue() - this.xAxis.getValue());
        int yAxisDelta = Math.abs(other.yAxis.getValue() - this.yAxis.getValue());

        return xAxisDelta == yAxisDelta;
    }

    public boolean isUpperThan(Position other) {
        return this.subtractYAxis(other) > 0;
    }

    public boolean isLowerThan(Position other) {
        return this.subtractYAxis(other) < 0;
    }

    public boolean isInVerticalRange(Position other, int range) {
        return Math.abs(this.yAxis.subtract(other.yAxis)) <= range;
    }

    public boolean isInVerticalRangeAndSameXAxis(Position other, int range) {
        return isInVerticalRange(other, range) && isSameXAxis(other);
    }

    public boolean isFartherThanOneStep(Position other) {
        int xAxisDelta = Math.abs(other.xAxis.getValue() - this.xAxis.getValue());
        int yAxisDelta = Math.abs(other.yAxis.getValue() - this.yAxis.getValue());

        return xAxisDelta > 1 || yAxisDelta > 1;
    }

    public List<Position> getPositionsSameYAxisBetween(Position other) {
        return YAxis.getBetween(this.yAxis, other.yAxis).stream()
                .map(yAxis -> Position.from(this.xAxis, yAxis))
                .collect(Collectors.toList());
    }

    public List<Position> getPositionsSameXAxisBetween(Position other) {
        return XAxis.getBetween(this.xAxis, other.xAxis).stream()
                .map(xAxis -> Position.from(xAxis, this.yAxis))
                .collect(Collectors.toList());
    }

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

    private Position getPositionWith(int xDir, int yDir, int idx) {
        XAxis xAxis1 = XAxis.getByValue(this.xAxis.getValue() + xDir * idx);
        YAxis yAxis1 = YAxis.getByValue(this.yAxis.getValue() + yDir * idx);

        return Position.from(xAxis1, yAxis1);
    }

    public XAxis getXAxis() {
        return xAxis;
    }

    public YAxis getYAxis() {
        return yAxis;
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
    }
}
