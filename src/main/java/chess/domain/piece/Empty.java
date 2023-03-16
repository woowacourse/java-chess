package chess.domain.piece;

import chess.domain.board.Position;
import java.util.List;

public class Empty extends Piece {

    private Empty(Color color) {
        super(color);
    }

    public static Piece create() {
        return new Empty(Color.EMPTY);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        return false;
    }

    @Override
    public List<Position> findPath(Position sourcePosition, Position targetPosition) {
        return null;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Piece move() {
        return null;
    }
}
