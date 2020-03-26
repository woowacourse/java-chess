package chess.domain.piece;

import java.util.List;

public class King extends Piece {
    public King(Position position, Color color) {
        super(position, color);
    }

    @Override
    protected List<Direction> movableDirections(Piece piece, Direction direction) {
        return Direction.everyDirection();
    }

    @Override
    protected Direction findDirection(int x, int y) {
        return Direction.of(x, y);
    }
}
