package chess.domain.piece.move.pawn;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.StraightDirection;

public class PawnMoveDoubleForwardChain extends PawnMoveChain {

    private final PawnMoveChain next;

    public PawnMoveDoubleForwardChain(PawnSupport support) {
        super(support);
        this.next = new PawnMoveDiagonalChain(support);
    }

    @Override
    public void move(Board board, Point from, Point to) {
        int horizontal = to.subtractHorizontal(from);
        int vertical = support.forwarding(to.subtractVertical(from));
        Point middle = from.next(StraightDirection.find(from, to));
        if (isStartLine(from) &&
                isToPoint(horizontal, vertical) &&
                isNoObstacle(board, to, middle)) {
            return;
        }
        next.move(board, from, to);
    }

    private boolean isStartLine(Point from) {
        return support.isStartLine(from);
    }

    private boolean isNoObstacle(Board board, Point to, Point middle) {
        return board.isEmpty(to) && board.isEmpty(middle);
    }

    private boolean isToPoint(int horizontal, int vertical) {
        return vertical == 2 && horizontal == 0;
    }
}
