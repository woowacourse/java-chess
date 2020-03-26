package chess.domain.piece;

import java.util.List;

public class Rook extends Piece {
    public Rook(Position position, Color color) {
        super(position, color);
    }

    @Override
    protected List<Direction> movableDirections(Piece piece, Direction direction) {
        return Direction.linearDirection();
    }

    @Override
    protected Direction findDirection(int x, int y) {
        return Direction.ofLinear(x, y);
    }
}
