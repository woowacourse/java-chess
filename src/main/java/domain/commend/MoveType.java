package domain.commend;

import domain.point.Point;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum MoveType {
    STRAIGHT((from, to) -> (from.getRowIndex() == to.getRowIndex()) ^ (from.getColumnIndex() == from.getColumnIndex())),
    DIAGONAL((from, to) -> Math.abs(from.getRowIndex() - to.getRowIndex()) == Math.abs(from.getColumnIndex() - to.getColumnIndex())),
    ELSE((from, to) -> false);

    private BiFunction<Point, Point, Boolean> isMoveType;

    MoveType(BiFunction<Point, Point, Boolean> isMoveType) {
        this.isMoveType = isMoveType;
    }

    public static MoveType getInstance(Point from, Point to) {
        return Arrays.stream(values())
            .filter(moveType -> moveType.isMoveType.apply(from, to))
            .findFirst()
            .orElse(ELSE);
    }
}
