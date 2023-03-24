package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        final int fileDifference = current.getFileDifference(destination);
        final int rankDifference = current.getRankDifference(destination);
        return PieceDirection.findStraightDirection(fileDifference, rankDifference);
    }
}
