package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public final class Knight extends Piece {

    private static final double POINT = 2.5;

    public Knight(Color color) {
        super(color);
    }

    // TODO 좀 더 나은 리팩터링 방법을 찾을 수 있을 것 같다
    @Override
    public boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = Direction.getKnightDirections();
        for (Direction direction : directions) {
            if (src.canMoveByTime(direction, dest, 1)) {
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
