package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        if (isDoublePawnPush(current, fileDifference, rankDifference)) {
            return getDoublePawnPushDirection();
        }
        return judgeDirection(fileDifference, rankDifference);
    }

    private boolean isDoublePawnPush(final Square current, final int fileDifference, final int rankDifference) {
        if (current.isRankTwo() && fileDifference == 0 && rankDifference == 2) {
            return true;
        }
        return current.isRankSeven() && fileDifference == 0 && rankDifference == -2;
    }

    private Direction getDoublePawnPushDirection() {
        if (team == Team.WHITE) {
            return Direction.UP;
        }
        return Direction.DOWN;
    }

    @Override
    protected Direction judgeDirection(final int fileDifference, final int rankDifference) {
        if (team == Team.WHITE) {
            return PieceDirection.WHITE_PAWN.findDirection(fileDifference, rankDifference);
        }
        return PieceDirection.BLACK_PAWN.findDirection(fileDifference, rankDifference);
    }
}
