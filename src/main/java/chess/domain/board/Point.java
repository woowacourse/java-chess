package chess.domain.board;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Point {
    private Coordinate x;
    private Coordinate y;

    private Point(int x, int y) {
        this.x = Coordinate.of(x);
        this.y = Coordinate.of(y);
    }

    private static Table<Coordinate, Coordinate, Point> TABLE;

    static {
        TABLE = HashBasedTable.create();
        for (int i = 0; i <= Coordinate.MAX_COORDINATE; i++) {
            for (int j = 0; j <= Coordinate.MAX_COORDINATE; j++) {
                TABLE.put(Coordinate.of(i), Coordinate.of(j), new Point(i, j));
            }
        }
    }

    public static Point of(int x, int y) {
        return TABLE.get(Coordinate.of(x), Coordinate.of(y));
    }

    public int xDistance(Point end) {
        return this.x.calculateDistance(end.x);
    }

    public int yDistance(Point end) {
        return this.y.calculateDistance(end.y);
    }

    public double calculateGradient(Point end) {
        if (this.x == end.x) {
            return Double.MAX_VALUE;
        }
        return ((double) this.y.calculateDistance(end.y)) / ((double) this.x.calculateDistance(end.x));
    }

    public int calculateMaxAbsoluteDistance(Point end) {
        int xDistance = this.xDistance(end);
        int yDistance = this.yDistance(end);
        return (xDistance != 0) ? Math.abs(xDistance) : Math.abs(yDistance);
    }

    public Point moveOneStep(DirectionType directionType, int size) {
        return Point.of(x.sumCoordinate(directionType.getxDegree() * size),
                y.sumCoordinate(directionType.getyDegree() * size));
    }

    public int getY() {
        return y.getCoordinate();
    }
}
