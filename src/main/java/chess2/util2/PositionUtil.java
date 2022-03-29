package chess2.util2;

import chess2.domain2.board2.Position;
import java.util.ArrayList;
import java.util.List;

public class PositionUtil {

    private PositionUtil() {
    }

    public static List<Position> positionsStraightBetween(Position from, Position to) {
        if (!isStraightPath(from, to)) {
            return List.of();
        }
        return positionsBetween(from, to);
    }

    private static List<Position> positionsBetween(Position from, Position to) {
        List<Position> positions = new ArrayList<>();
        Position next = from.oneStepToward(to);
        while (next != to) {
            positions.add(next);
            next = next.oneStepToward(to);
        }
        return positions;
    }

    public static boolean isStraightPath(Position from, Position to) {
        return isHorizontalOrVertical(from, to) || isDiagonal(from, to);
    }

    public static boolean isDiagonal(Position from, Position to) {
        return from.fileDifference(to)
                == from.rankDifference(to);
    }

    public static boolean isHorizontalOrVertical(Position from, Position to) {
        return isHorizontal(from, to) || isVertical(from, to);
    }

    private static boolean isHorizontal(Position from, Position to) {
        return from.fileDifference(to) == 0
                && from.rankDifference(to) > 0;
    }

    private static boolean isVertical(Position from, Position to) {
        return from.fileDifference(to) > 0
                && from.rankDifference(to) == 0;
    }
}
