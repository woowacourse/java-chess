package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public boolean isSameXAxis(Position other) {
        return this.xAxis.equals(other.xAxis);
    }

    public boolean isSameYAxis(Position other) {
        return this.yAxis.equals(other.yAxis);
    }

    public boolean isOnDiagonal(Position other) {
        int xAxisDelta = Math.abs(other.xAxis.ordinal() - this.xAxis.ordinal());
        int yAxisDelta = Math.abs(other.yAxis.ordinal() - this.yAxis.ordinal());

        return xAxisDelta == yAxisDelta;
    }

    public boolean isFarFromMoreThanOne(Position other) {
        int xAxisDelta = Math.abs(other.xAxis.ordinal() - this.xAxis.ordinal());
        int yAxisDelta = Math.abs(other.yAxis.ordinal() - this.yAxis.ordinal());

        return xAxisDelta > 1 || yAxisDelta > 1;
    }

    public boolean isOnSevenShape(Position other) {
        boolean condition1 = Math.abs(this.xAxis.ordinal() - other.xAxis.ordinal()) == 2
                && Math.abs(this.yAxis.ordinal() - other.yAxis.ordinal()) == 1;

        boolean condition2 = Math.abs(this.xAxis.ordinal() - other.xAxis.ordinal()) == 1
                && Math.abs(this.yAxis.ordinal() - other.yAxis.ordinal()) == 2;

        return condition1 || condition2;
    }

    @Override
    public String toString() {
        return "Position{" +
                "XAxis=" + xAxis +
                ", YAxis=" + yAxis +
                '}';
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
