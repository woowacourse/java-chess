package chess.domain.move;

import chess.domain.Position;

public class MoveTypeFactory {

    private static final int ONE_ABSOLUTE_DISTANCE = 1;
    private static final int TWO_ABSOLUTE_DISTANCE = 2;

    public static MoveType of(Position source, Position target) {
        if (isStraightType(source, target)) {
            return new StraightType(source, target);
        }
        if (isCrossType(source, target)) {
            return new CrossType(source, target);
        }
        if (isKnightType(source, target)) {
            return new KnightType(target);
        }
        return null;
    }

    private static boolean isKnightType(Position source, Position target) {
        return (Math.abs(source.calculateXPositionDistance(target)) == ONE_ABSOLUTE_DISTANCE
                && Math.abs(source.calculateYPositionDistance(target)) == TWO_ABSOLUTE_DISTANCE)
                || (Math.abs(source.calculateYPositionDistance(target)) == ONE_ABSOLUTE_DISTANCE
                && Math.abs(source.calculateXPositionDistance(target)) == TWO_ABSOLUTE_DISTANCE);
    }

    private static boolean isCrossType(Position source, Position target) {
        return Math.abs(source.calculateYPositionDistance(target))
                == Math.abs(source.calculateXPositionDistance(target));
    }

    private static boolean isStraightType(Position source, Position target) {
        return source.isSameRank(target) || source.isSameFile(target);
    }
}
