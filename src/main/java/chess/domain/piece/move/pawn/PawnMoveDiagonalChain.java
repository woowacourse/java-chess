package chess.domain.piece.move.pawn;

import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;

public class PawnMoveDiagonalChain extends PawnMoveChain {

    public PawnMoveDiagonalChain(PawnSupport support) {
        super(support);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        int horizontal = Math.abs(route.subtractHorizontal());
        int vertical = support.forwarding(route.subtractVertical());
        return isToPoint(horizontal, vertical) && !emptyPoints.contains(route.getDestination());
    }

    private boolean isToPoint(int horizontal, int vertical) {
        return vertical == 1 && horizontal == 1;
    }
}
