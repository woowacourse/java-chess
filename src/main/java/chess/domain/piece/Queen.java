package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        final int fileDifference = current.getFileDifference(destination);
        final int rankDifference = current.getRankDifference(destination);
        return PieceDirection.KING_AND_QUEEN.findDirection(fileDifference, rankDifference);
    }
}
