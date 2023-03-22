package chess.domain.piece;

import chess.domain.square.Direction;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public Direction judgeDirection(final int fileDifference, final int rankDifference) {
        return PieceDirection.KING_AND_QUEEN.findDirection(fileDifference, rankDifference);
    }
}
