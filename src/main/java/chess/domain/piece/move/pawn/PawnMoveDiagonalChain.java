package chess.domain.piece.move.pawn;

import chess.domain.board.Board;
import chess.domain.board.Point;

public class PawnMoveDiagonalChain extends PawnMoveChain {

    public PawnMoveDiagonalChain(PawnSupport support) {
        super(support);
    }

    @Override
    public void move(Board board, Point from, Point to) {
        int horizontal = to.subtractHorizontal(from);
        int vertical = support.forwarding(to.subtractVertical(from));
        if (isToPoint(horizontal, vertical) &&
                isEnemyExist(board, to) &&
                isNotStartLine(from)) {
            return;
        }
        throw new IllegalArgumentException("");
    }

    private boolean isNotStartLine(Point from) {
        return !support.isStartLine(from);
    }

    private boolean isEnemyExist(Board board, Point to) {
        return !board.isEmpty(to);
    }

    private boolean isToPoint(int horizontal, int vertical) {
        return vertical == 1 && horizontal == 1;
    }
}
