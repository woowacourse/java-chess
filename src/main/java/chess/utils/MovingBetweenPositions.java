package chess.utils;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class MovingBetweenPositions {

    public static List<Position> computeBetweenPositionsOfRow(Position source, Position target) {
        if (source.isSmallColumn(target)) {
            return generateRowPositions(source, target);
        }
        return generateRowPositions(target, source);
    }

    public static List<Position> computeBetweenPositionsOfColumn(Position source, Position target) {
        if (source.isSmallRow(target)) {
            return generateColumnPositions(source, target);
        }
        return generateColumnPositions(target, source);
    }

    public static List<Position> computeLeftDownRightUp(Position source, Position target) {
        if (source.isSmallRow(target)) {
            return generateLeftDownRightUpPositions(source, target);
        }
        return generateLeftDownRightUpPositions(target, source);
    }

    public static List<Position> computeLeftUpRightDown(Position source, Position target) {
        if (source.isSmallRow(target)) {
            return generateLeftUpRightDownPositions(source, target);
        }
        return generateLeftUpRightDownPositions(target, source);
    }


    private static List<Position> generateLeftDownRightUpPositions(Position source, Position target) {
        List<Position> list = new ArrayList<>();
        int count = source.gapTwoPositionRow(target);

        for (int index = 1; index < count; index++) {
            list.add(source.findPossiblePosition(index, -index));
        }
        return list;
    }

    private static List<Position> generateLeftUpRightDownPositions(Position min, Position max) {
        List<Position> list = new ArrayList<>();
        int count = max.gapTwoPositionRow(min);

        for (int index = 1; index < count; index++) {
            list.add(min.findPossiblePosition(index, -index));
        }
        return list;
    }

    private static List<Position> generateRowPositions(Position source, Position target) {
        List<Position> list = new ArrayList<>();
        int count = source.gapTwoPositionColumn(target);

        for (int index = 1; index < count; index++) {
            list.add(source.findPossiblePosition(0, index));
        }
        return list;
    }

    private static List<Position> generateColumnPositions(Position source, Position target) {
        List<Position> list = new ArrayList<>();
        int count = source.gapTwoPositionRow(target);

        for (int index = 1; index < count; index++) {
            list.add(source.findPossiblePosition(index, 0));
        }
        return list;
    }
}
