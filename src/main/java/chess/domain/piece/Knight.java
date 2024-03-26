package chess.domain.piece;

import chess.domain.board.Route;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Route route) {
        return route.isSizeOf(2)
                && route.hasNoAllyAtTarget()
                && route.containsDiagonal()
                && route.containsOrthogonal();
    }
}
