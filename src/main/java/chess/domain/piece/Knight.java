package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class Knight extends Piece {

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
        int distanceSum = Math.abs(next.xDistance(prev)) + Math.abs(next.yDistance(prev));
        if (distanceSum == 3) {
            return true;
        }
        return false;
    }

    @Override
    public String pieceToString() {
        return isWhite() ? "n" : "N";
    }
}
