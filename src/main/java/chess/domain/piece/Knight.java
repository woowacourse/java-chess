package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public final class Knight extends Piece {

    private static final double POINT = 2.5;

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = Direction.getKnightDirections();
        for (Direction direction : directions) {
            if (src.canReachByMovingTo(direction, dest, 1)) {
                return direction;
            }
        }
        return null;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
