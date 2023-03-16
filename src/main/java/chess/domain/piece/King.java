package chess.domain.piece;

import chess.domain.board.Position;
import java.util.Collections;
import java.util.List;

public class King extends Piece {

    private final boolean isMove;

    public King(Color color, boolean isMove) {
        super(color);
        this.isMove = isMove;
    }

    public King(Color color) {
        super(color);
        this.isMove = false;
    }

    @Override
    boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        int sourceColumnNumber = sourcePosition.getColumn();
        int targetColumnNumber = targetPosition.getColumn();
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();

        return Math.abs(sourceColumnNumber - targetColumnNumber) <= 1
                && Math.abs(sourceRankNumber - targetRankNumber) <= 1
                && isNotMyPosition(sourcePosition, targetPosition);
    }

    @Override
    List<Position> findPath(Position sourcePosition, Position targetPosition) {
        return Collections.emptyList();
    }

    @Override
    boolean isKing() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    Piece move() {
        return null;
    }
}
