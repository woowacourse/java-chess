package chess.domain.piece;

import chess.domain.board.Position;
import java.util.List;

public class Rook extends Piece {

    private final boolean isMove;

    public Rook(Color color, boolean isMove) {
        super(color);
        this.isMove = isMove;
    }

    public Rook(Color color) {
        super(color);
        this.isMove = false;
    }

    @Override
    boolean canMove(Position sourcePosition, Position targetPosition) {
        return isStraight(sourcePosition, targetPosition) && isNotMyPosition(sourcePosition, targetPosition);
    }

    @Override
    List<Position> findPath(Position sourcePosition, Position targetPosition) {
        return null;
    }

    @Override
    boolean isKing() {
        return false;
    }

    @Override
    Piece move() {
        return null;
    }
}
