package chess.domain.piece.move.pawn;

import chess.domain.board.Board;
import chess.domain.board.Point;

public class PawnMoveDiagonalChain extends PawnMoveChain {

    public PawnMoveDiagonalChain(PawnSupport support) {
        super(support);
    }

    @Override
    public boolean move(Board board, Point from, Point to) {
        int horizontal = Math.abs(to.subtractHorizontal(from));
        int vertical = support.forwarding(to.subtractVertical(from));
        return isToPoint(horizontal, vertical) && isEnemyExist(board, to);
    }

    private boolean isEnemyExist(Board board, Point to) {
        return !board.isEmpty(to);
    }

    private boolean isToPoint(int horizontal, int vertical) {
        return vertical == 1 && horizontal == 1;
    }
}
