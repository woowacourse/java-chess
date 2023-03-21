package chess.domain.piece;

import chess.domain.exception.WrongDirectionException;
import chess.domain.square.Direction;
import chess.domain.square.Square;

public class King extends Piece {

    private final static int MOVE_RANGE = 1;

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        final int fileDifference = current.getFileDifference(destination);
        final int rankDifference = current.getRankDifference(destination);
        validateDifference(fileDifference);
        validateDifference(rankDifference);
        return PieceDirection.KING_AND_QUEEN.findDirection(fileDifference, rankDifference);
    }

    private void validateDifference(final int difference) {
        if (Math.abs(difference) > MOVE_RANGE) {
            throw new WrongDirectionException();
        }
    }
}
