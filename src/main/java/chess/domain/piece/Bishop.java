package chess.domain.piece;

import chess.domain.board.Route;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Route route) {
        if (route.hasPiecePathExcludedTarget()) {
            return false;
        }
        if (route.containsOrthogonal()) {
            return false;
        }
        return route.hasNoAllyAtTarget() && route.categoryNumOf(1);
    }
}
