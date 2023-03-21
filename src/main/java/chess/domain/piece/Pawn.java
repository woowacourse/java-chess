package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        if (team == Team.WHITE) {
            return findWhiteDirection(current, destination);
        }
        return findBlackDirection(current, destination);
    }

    private Direction findWhiteDirection(final Square current, final Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        if (current.isRankTwo() && fileDifference == 0 && rankDifference == 2) {
            return Direction.UP;
        }
        return PieceDirection.WHITE_PAWN.findDirection(fileDifference, rankDifference);
    }

    private Direction findBlackDirection(final Square current, final Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        if (current.isRankSeven() && fileDifference == 0 && rankDifference == -2) {
            return Direction.DOWN;
        }
        return PieceDirection.BLACK_PAWN.findDirection(fileDifference, rankDifference);
    }
}
