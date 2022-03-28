package chess.utils;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class BetweenPositionsGenerator {

    private BetweenPositionsGenerator() {
    }

    public static List<Position> computeBetweenPositionBySameRow(Position source, Position target) {
        if (source.isSmallColumn(target)) {
            return generatePositionBySameRow(source, target);
        }
        return generatePositionBySameRow(target, source);
    }

    public static List<Position> computeBetweenPositionBySameColumn(Position source, Position target) {
        if (source.isSmallRow(target)) {
            return generatePositionBySameColumn(source, target);
        }
        return generatePositionBySameColumn(target, source);
    }

    public static List<Position> computeBetweenPositionDiagonal(Position source, Position target) {
        if (source.isSmallRow(target)) {
            return generateDiagonalPositions(source, target);
        }
        return generateDiagonalPositions(target, source);
    }

    private static List<Position> generateDiagonalPositions(Position source, Position target) {
        List<Position> positions = new ArrayList<>();
        int gap = source.gapTwoPositionRow(target);
        for (int index = 1; index < gap; index++) {
            positions.add(source.findPossiblePosition(index, -index));
        }
        return positions;
    }

    private static List<Position> generatePositionBySameRow(Position source, Position target) {
        List<Position> positions = new ArrayList<>();
        int gap = source.gapTwoPositionColumn(target);
        for (int index = 1; index < gap; index++) {
            positions.add(source.findPossiblePosition(0, index));
        }
        return positions;
    }

    private static List<Position> generatePositionBySameColumn(Position source, Position target) {
        List<Position> positions = new ArrayList<>();
        int gap = source.gapTwoPositionRow(target);
        for (int index = 1; index < gap; index++) {
            positions.add(source.findPossiblePosition(index, 0));
        }
        return positions;
    }
}
