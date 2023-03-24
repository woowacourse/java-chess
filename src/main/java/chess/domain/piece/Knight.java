package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        final int fileDifference = current.getFileDifference(destination);
        final int rankDifference = current.getRankDifference(destination);
        return PieceDirection.findKnightDirection(fileDifference, rankDifference);
    }
}
