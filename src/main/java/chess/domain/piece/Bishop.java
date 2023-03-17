package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.PieceDirection;
import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Bishop extends Piece {

    public Bishop(final Color color, final MoveStrategy moveStrategy) {
        super(color, moveStrategy);
    }

    @Override
    public Direction findDirection(Square current, Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        return PieceDirection.DIAGONAL.findDirection(fileDifference, rankDifference);
    }
}
