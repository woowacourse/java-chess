package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class Knight extends Piece {
    private static final int MOVE_DISTANCE = 3;

    public Knight(PlayerType playerType) {
        super(playerType);
    }

    @Override
    public boolean isMovable(Point prev, Point next) {
        double gradient = Math.abs(prev.calculateGradient(next));
        if (gradient == 0) {
            return false;
        }
        if (gradient == Double.MAX_VALUE) {
            return false;
        }
        return Math.abs(next.xDistance(prev)) + Math.abs(next.yDistance(prev)) == MOVE_DISTANCE;
    }

    @Override
    public String pieceToString() {
        return isWhite() ? "n" : "N";
    }
}
