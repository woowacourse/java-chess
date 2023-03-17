package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Direction findDirection(Square current, Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        return PieceDirection.KNIGHT.findDirection(fileDifference, rankDifference);
    }
}
