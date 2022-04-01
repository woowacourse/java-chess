package chess.domain.piece.move.pawn;

import chess.domain.board.EmptyPoints;
import chess.domain.board.Point;
import chess.domain.board.Route;
import chess.domain.piece.move.straight.StraightDirection;

public class PawnMoveDoubleForwardChain extends PawnMoveChain {

    private final PawnMoveChain next;

    public PawnMoveDoubleForwardChain(PawnSupport support) {
        super(support);
        this.next = new PawnMoveDiagonalChain(support);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        int horizontal = route.subtractHorizontal();
        int vertical = support.forwarding(route.subtractVertical());
        if (isStartLine(route.getSource()) &&
            isToPoint(horizontal, vertical) &&
            isNoObstacle(route, emptyPoints)) {
            return true;
        }
        return next.move(route, emptyPoints);
    }

    private boolean isStartLine(Point from) {
        return support.isStartLine(from);
    }

    private boolean isToPoint(int horizontal, int vertical) {
        return vertical == 2 && horizontal == 0;
    }

    private boolean isNoObstacle(Route route, EmptyPoints emptyPoints) {
        Point from = route.getSource();
        Point next = from.next(StraightDirection.find(route));
        return emptyPoints.contains(route.getDestination()) && emptyPoints.contains(next);
    }
}
