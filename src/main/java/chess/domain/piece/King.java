package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class King implements Piece {
    private PlayerType playerType;

    public King(PlayerType playerType) {
        this.playerType = playerType;
    }

    @Override
    public boolean isMovable(Point prev, Point next) {
        double gradient = Math.abs(prev.calculateGradient(next));
        int distanceSum = Math.abs(next.xDistance(prev)) + Math.abs(next.yDistance(prev));
        if (gradient == 1 && distanceSum == 2) {
            return true;
        }
        if (gradient == 0 && distanceSum == 1) {
            return true;
        }
        if (gradient == Double.MAX_VALUE && distanceSum == 1) {
            return true;
        }
        return false;
    }
}
