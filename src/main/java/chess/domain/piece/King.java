package chess.domain.piece;

import chess.domain.square.Direction;

public class King extends Piece {

    public King(final Team team) {
        super(team);
    }

    @Override
    protected Direction judgeDirection(final int fileDifference, final int rankDifference) {
        validateDifference(fileDifference);
        validateDifference(rankDifference);
        return PieceDirection.KING_AND_QUEEN.findDirection(fileDifference, rankDifference);
    }

    private void validateDifference(final int difference) {
        if (Math.abs(difference) > 1) {
            throw new IllegalArgumentException("King은 1칸만 이동할 수 있습니다.");
        }
    }
}
