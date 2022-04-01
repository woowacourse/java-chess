package chess.domain.position;

import java.util.Arrays;

public enum Direction {

    RIGHT(0, 1, 180),
    LEFT(0, -1, 0),
    TOP(-1, 0, 90),
    BOTTOM(1, 0, -90),
    TOP_RIGHT(-1, 1, 135),
    TOP_LEFT(-1, -1, 45),
    BOTTOM_RIGHT(1, 1, -135),
    BOTTOM_LEFT(1, -1, -45)
    ;

    private final int row;
    private final int col;
    private final double degree;

    Direction(int row, int col, double degree) {
        this.row = row;
        this.col = col;
        this.degree = degree;
    }
    
    public static Direction of(Position source, Position target) {
        int ordinateCoordinate = source.getRankIndex() - target.getRankIndex();
        int abscissaCoordinate = source.getFileIndex() - target.getFileIndex();
        double degree = getDegree(ordinateCoordinate, abscissaCoordinate);

        return Arrays.stream(values())
                .filter(direction -> direction.degree == degree)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static double getDegree(int ordinateCoordinate, int abscissaCoordinate) {
        double radian = Math.atan2(ordinateCoordinate, abscissaCoordinate);
        return radian * 180 / Math.PI;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
