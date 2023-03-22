package chess.domain.piece;

import chess.domain.square.Direction;

public class Knight extends Piece {

    public Knight(final Team team) {
        super(team);
    }

    @Override
    protected Direction judgeDirection(final int fileDifference, final int rankDifference) {
        return PieceDirection.KNIGHT.findDirection(fileDifference, rankDifference);
    }
}
