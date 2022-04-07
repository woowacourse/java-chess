package chess.domain.piece.move.pawn;

import chess.domain.board.Point;
import chess.domain.piece.Piece;

import java.util.Map;

public class PawnMoveDiagonalChain extends PawnMoveChain {

    public PawnMoveDiagonalChain(PawnSupport support) {
        super(support);
    }

    @Override
    public void move(Map<Point, Piece> pointPieces, Point from, Point to) {
        int horizontal = to.subtractHorizontal(from);
        int vertical = support.forwarding(to.subtractVertical(from));
        if (isToPoint(horizontal, vertical) &&
                isEnemyExist(pointPieces, to)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 폰이 이동할 수 없는 위치입니다.");
    }

    private boolean isEnemyExist(Map<Point, Piece> pointPieces, Point to) {
        return !isEmptyPoint(pointPieces, to);
    }

    private boolean isToPoint(int horizontal, int vertical) {
        return vertical == 1 && Math.abs(horizontal) == 1;
    }
}
