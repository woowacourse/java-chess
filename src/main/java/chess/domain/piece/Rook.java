package chess.domain.piece;

import chess.domain.board.Route;

public class Rook extends Piece {
    private static final int ONE_DIRECTION = 1;

    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean canMove(Route route) {
        if (route.hasPiecePathExclusive()) {
            return false;
        }
        if (route.containsDiagonal()) {
            return false;
        }
        return route.hasNoAllyAtTarget() && route.isDirectionsCount(ONE_DIRECTION);
    }
}
