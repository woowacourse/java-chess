package chess.domain.piece;

import chess.domain.board.Route;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Route route) {
        if (route.hasPiecePathExclusive()) {
            return false;
        }
        if (route.containsOrthogonal()) {
            return false;
        }
        return route.hasNoAllyAtTarget() && route.isDirectionsCount(1);
    }
}
