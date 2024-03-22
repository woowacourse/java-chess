package chess.domain.piece;

import chess.domain.board.Route;

public class King extends Piece {

    private static final String KING_NAME = "K";
    private static final int MAX_MOVE_DISTANCE = 1;

    public King(Color color) {
        super(color, KING_NAME);
    }

    @Override
    public boolean canMove(Route route) {
        return route.isSizeOf(MAX_MOVE_DISTANCE) && route.hasNoAllyAtTarget();
    }
}
