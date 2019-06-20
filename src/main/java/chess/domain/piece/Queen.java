package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class Queen implements Piece {
    private PlayerType playerType;

    public Queen(PlayerType playerType) {
        this.playerType = playerType;
    }

    @Override
    public boolean isMovable(Point prev, Point next) {
        double gradient = Math.abs(prev.calculateGradient(next));
        if (gradient == 1) {
            return true;
        }
        if (gradient == 0) {
            return true;
        }
        if (gradient == Double.MAX_VALUE) {
            return true;
        }
        return false;
    }
}
