package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public class Bishop extends Piece {
    public Bishop(PlayerType playerType) {
        super(playerType);
    }

    @Override
    public boolean isMovable(Point prev, Point next) {
        double gradient = Math.abs(prev.calculateGradient(next));
        if (gradient == 1) {
            return true;
        }
        return false;
    }
}
