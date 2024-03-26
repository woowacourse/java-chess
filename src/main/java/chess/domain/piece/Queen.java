package chess.domain.piece;

import chess.domain.board.Route;

public class Queen extends Piece {
    private static final int ONE_DIRECTION = 1;

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean canMove(Route route) {
        if (route.hasPiecePathExclusive()) {
            return false;
        }
        return route.hasNoAllyAtTarget() && route.isDirectionsCount(ONE_DIRECTION);
    }
}

