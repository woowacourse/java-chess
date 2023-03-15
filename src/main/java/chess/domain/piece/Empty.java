package chess.domain.piece;

import chess.domain.board.Position;
import java.util.List;

public class Empty extends Piece {

    public Empty(Color color) {
        super(color);
    }

    @Override
    boolean canMove(Position sourcePosition, Position targetPosition) {
        return false;
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
