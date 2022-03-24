package chess.domain.position;

public enum Direction {

    VERTICAL,
    HORIZONTAL,
    DIAGONAL,
    IGNORE;

    public static Direction calculate(Position source, Position target) {
        if (source.isVertical(target)) {
            return VERTICAL;
        }
        if (source.isHorizontal(target)) {
            return HORIZONTAL;
        }
        if (source.isDiagonal(target)) {
            return DIAGONAL;
        }
        return IGNORE;
    }
}
