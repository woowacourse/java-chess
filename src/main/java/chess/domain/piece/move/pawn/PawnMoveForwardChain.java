package chess.domain.piece.move.pawn;

import chess.domain.board.Point;
import chess.domain.piece.Piece;

import java.util.Map;

public class PawnMoveForwardChain extends PawnMoveChain {

    private final PawnMoveChain next;

    public PawnMoveForwardChain(PawnSupport support) {
        super(support);
        this.next = new PawnMoveDoubleForwardChain(support);
    }

    @Override
    public void move(Map<Point, Piece> pointPieces, Point from, Point to) {
        int horizontal = to.subtractHorizontal(from);
        int vertical = support.forwarding(to.subtractVertical(from));
        if (vertical == 1 && horizontal == 0 && isEmptyPoint(pointPieces, to)) {
            return;
        }
        next.move(pointPieces, from, to);
    }
}
