package chess.domain.piece;

import chess.domain.board.Route;

public class King extends Piece {
    private static final int MAX_MOVE_DISTANCE = 1;

    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean canMove(Route route) {
        return route.isSizeOf(MAX_MOVE_DISTANCE) && route.hasNoAllyAtTarget();
    }
}
