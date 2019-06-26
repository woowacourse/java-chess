package chess.domain.piece;

import chess.domain.board.DirectionType;
import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class Bishop extends Piece {
    public Bishop(PlayerType playerType) {
        super(playerType);
    }

    @Override
    public boolean isMovable(Point prev, Point next) {
        return Math.abs(prev.calculateGradient(next)) == 1;
        // TODO: 2019-06-26 Use DirectionType~!
//        DirectionType directionType = DirectionType.valueOf(prev, next);
//        return DirectionType.diagonalDirection().contains(directionType);
    }

    @Override
    public String pieceToString() {
        return isWhite() ? "b" : "B";
    }
}
