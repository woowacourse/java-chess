package chess.domain.piece.move.pawn;

import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;

public class PawnMoveForwardChain extends PawnMoveChain {

    private final PawnMoveChain next;

    public PawnMoveForwardChain(PawnSupport support) {
        super(support);
        this.next = new PawnMoveDoubleForwardChain(support);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        int horizontal = route.subtractHorizontal();
        int vertical = support.forwarding(route.subtractVertical());
        if (isToPoint(horizontal, vertical) && emptyPoints.contains(route.getDestination())) {
            return true;
        }
        return next.move(route, emptyPoints);
    }

    private boolean isToPoint(int horizontal, int vertical) {
        return vertical == 1 && horizontal == 0;
    }
}
