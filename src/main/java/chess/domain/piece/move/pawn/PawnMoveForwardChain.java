package chess.domain.piece.move.pawn;

import chess.domain.board.Board;
import chess.domain.board.Point;

public class PawnMoveForwardChain extends PawnMoveChain {

    private final PawnMoveChain next;

    public PawnMoveForwardChain(PawnSupport support) {
        super(support);
        this.next = new PawnMoveDoubleForwardChain(support);
    }

    @Override
    public boolean move(Board board, Point from, Point to) {
        int horizontal = to.subtractHorizontal(from);
        int vertical = support.forwarding(to.subtractVertical(from));
        if (vertical == 1 && horizontal == 0 && board.isEmpty(to)) {
            return true;
        }
        return next.move(board, from, to);
    }
}
