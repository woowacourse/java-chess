package chess.domain.piece;

import chess.domain.board.Route;

public class Bishop extends Piece {

    private static final String BISHOP_NAME = "B";

    public Bishop(Color color) {
        super(color, BISHOP_NAME);
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
