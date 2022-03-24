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

    public boolean isIgnore() {
        return this == IGNORE;
    }

    public boolean isVertical() {
        return this == VERTICAL;
    }

    public boolean isHorizontal() {
        return this == HORIZONTAL;
    }

    public boolean isDiagonal() {
        return this == DIAGONAL;
    }
}
