package chess.domain.move;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;

public class MoveFactory {
    private static final int ONE_POINT = 1;
    private static final int TWO_POINT = 2;

    public static Move findMovePattern(Position source, Position target) {
        if (isCrossPattern(source, target)) {
            return new CrossMove(source, target);
        }
        if (isStraightPattern(source, target)) {
            return new StraightMove(source, target);
        }
        if (isKnightPattern(source, target)) {
            return new KnightMove();
        }
        throw new IllegalArgumentException(Piece.ERROR_MESSAGE_NOT_MOVABLE);
    }

    private static boolean isCrossPattern(Position source, Position target) {
        return Math.abs(source.calculateRankDistance(target))
                == Math.abs(source.calculateFileDistance(target));
    }

    private static boolean isKnightPattern(Position source, Position target) {
        return (Math.abs(source.calculateFileDistance(target)) == ONE_POINT
                && Math.abs(source.calculateRankDistance(target)) == TWO_POINT)
                || (Math.abs(source.calculateRankDistance(target)) == ONE_POINT
                && Math.abs(source.calculateFileDistance(target)) == TWO_POINT);
    }

    private static boolean isStraightPattern(Position source, Position target) {
        return source.isSameRank(target) || source.isSameXPosition(target);
    }
}
