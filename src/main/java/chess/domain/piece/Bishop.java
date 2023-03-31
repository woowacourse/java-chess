package chess.domain.piece;

import chess.domain.square.Direction;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    protected Direction judgeDirection(final int fileDifference, final int rankDifference) {
        return PieceDirection.DIAGONAL.findDirection(fileDifference, rankDifference);
    }
}
