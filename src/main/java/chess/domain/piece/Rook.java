package chess.domain.piece;

import chess.domain.square.Direction;

public class Rook extends Piece {

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public Direction judgeDirection(final int fileDifference, final int rankDifference) {
        return PieceDirection.STRAIGHT.findDirection(fileDifference, rankDifference);
    }
}
