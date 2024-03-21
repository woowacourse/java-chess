package chess.domain.piece;

import chess.domain.Movement;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public boolean canMove(final Movement movement) {
        int fileDiff = Math.abs(movement.getFileDifference());
        int rankDiff = Math.abs(movement.getRankDifference());

        return (fileDiff == 2 && rankDiff == 1) || (fileDiff == 1 && rankDiff == 2);
    }
}
