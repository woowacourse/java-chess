package domain.position;

public enum Direction {

    CROSS,
    DIAGONAL,
    OTHER;

    public static Direction of(Position start, Position end) {
        int rowGap = start.calculateRowGap(end);
        int columnGap = start.calculateColumnGap(end);

        if (rowGap == 0 || columnGap == 0) {
            return CROSS;
        }
        if (Math.abs(rowGap) == Math.abs(columnGap)) {
            return DIAGONAL;
        }
        return OTHER;
    }
}
