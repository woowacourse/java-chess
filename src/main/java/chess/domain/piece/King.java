package chess.domain.piece;

import chess.domain.piece.exception.WrongDirectionException;
import chess.domain.square.Direction;
import chess.domain.square.Square;

public class King extends Piece {

    public King(final Team team) {
        super(team);
    }

    @Override
    public Direction findDirection(Square current, Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        validateDifference(fileDifference);
        validateDifference(rankDifference);
        return PieceDirection.KING_AND_QUEEN.findDirection(fileDifference, rankDifference);
    }

    private void validateDifference(final int difference) {
        if (Math.abs(difference) > 1) {
            throw new WrongDirectionException();
        }
    }
}
