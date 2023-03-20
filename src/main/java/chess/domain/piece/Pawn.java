package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Pawn extends Piece {
    private static final int WHITE_PAWN_INIT_Y_POS = 1;
    private static final int BLACK_PAWN_INIT_Y_POS = 6;
    private static final int PAWN_SPECIAL_DISTANCE = 2;
    private static final int PAWN_DEFAULT_DISTANCE = 1;

    public Pawn(Team team) {
        super(Role.PAWN, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        if (source.isSameYAs(target)) {
            return false;
        }
        if (isWhitePawnReverseDirection(source, target) || isBlackPawnReverseDirection(source, target)) {
            return false;
        }
        int distance = source.getDistanceTo(target);
        if (canMoveInitialDistance(source, distance)) {
            return true;
        }
        return distance == PAWN_DEFAULT_DISTANCE;
    }

    private boolean isWhitePawnReverseDirection(Position source, Position target) {
        return this.team == Team.WHITE && source.isOverThanYAs(target);
    }

    private boolean isBlackPawnReverseDirection(Position source, Position target) {
        return this.team == Team.BLACK && target.isOverThanYAs(source);
    }

    private boolean canMoveInitialDistance(Position source, int distance) {
        return distance == PAWN_SPECIAL_DISTANCE && isPawnInitialPosition(source);
    }

    private boolean isPawnInitialPosition(Position source) {
        return source.isSameY(WHITE_PAWN_INIT_Y_POS) || source.isSameY(BLACK_PAWN_INIT_Y_POS);
    }
}
