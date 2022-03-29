package chess.domain.piece;

import chess.domain.board.Position;
import java.util.function.BiPredicate;

public enum Type {
    BISHOP(Position::inDiagonalWith),
    KING(Position::isNextTo),
    KNIGHT(Type::canKnightApproach),
    PAWN(Position::isNextTo),
    QUEEN((sourcePosition, targetPosition) -> {
        if (sourcePosition.inSameColumnWith(targetPosition)) {
            return true;
        }
        if (sourcePosition.inSameRowWith(targetPosition)) {
            return true;
        }
        return sourcePosition.inDiagonalWith(targetPosition);
    }),
    ROOK((sourcePosition, targetPosition) -> {
        if (sourcePosition.inSameColumnWith(targetPosition)) {
            return true;
        }
        return sourcePosition.inSameRowWith(targetPosition);
    }),
    NONE((sourcePosition, targetPosition) -> false);

    private static final int DISTANCE_KNIGHT_FIRST_STEP = 2;
    private static final int DISTANCE_KNIGHT_SECOND_STEP = 1;
    private final BiPredicate<Position, Position> distanceChecker;

    Type(BiPredicate<Position, Position> distanceChecker) {
        this.distanceChecker = distanceChecker;
    }

    private static boolean canKnightApproach(Position sourcePosition, Position targetPosition) {
        int columnDistance = sourcePosition.columnDistance(targetPosition);
        int rowDistance = sourcePosition.rowDistance(targetPosition);
        if (rowDistance == DISTANCE_KNIGHT_FIRST_STEP && columnDistance == DISTANCE_KNIGHT_SECOND_STEP) {
            return true;
        }
        return columnDistance == DISTANCE_KNIGHT_FIRST_STEP && rowDistance == DISTANCE_KNIGHT_SECOND_STEP;
    }

    public boolean canApproach(Position sourcePosition, Position targetPosition) {
        return this.distanceChecker.test(sourcePosition, targetPosition);
    }
}
