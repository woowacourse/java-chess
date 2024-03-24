package chess.domain.piece;

import chess.domain.board.Route;

public class Knight extends Piece {

    private static final String KNIGHT_NAME = "N";

    public Knight(Color color) {
        super(color, KNIGHT_NAME);
    }

    @Override
    public boolean canMove(Route route) {
        return route.isSizeOf(2)
                && route.hasNoAllyAtTarget()
                && route.containsDiagonal()
                && route.containsOrthogonal();
    }
}
