package chess.strategy;

import chess.domain.Position;

public class PawnStrategy implements MoveStrategy {

    private static final int WHITE_PAWN_INIT_Y_POS = 1;
    private static final int BLACK_PAWN_INIT_Y_POS = 6;
    private static final int PAWN_SPECIAL_DISTANCE = 2;
    private static final int PAWN_DEFAULT_DISTANCE = 1;

    @Override
    public boolean isMovable(Position source, Position target) {
        if (source.isSameYAs(target)) {
            return false;
        }

        int distance = source.getDistanceTo(target);
        if (canMoveInitialDistance(source, distance)) {
            return true;
        }
        return distance == PAWN_DEFAULT_DISTANCE;
    }

    private boolean canMoveInitialDistance(Position source, int distance) {
        return distance == PAWN_SPECIAL_DISTANCE && isPawnInitialPosition(source);
    }

    private boolean isPawnInitialPosition(Position source) {
        return source.isSameY(WHITE_PAWN_INIT_Y_POS) || source.isSameY(BLACK_PAWN_INIT_Y_POS);
    }
}
