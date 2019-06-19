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

}
