package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public final class King extends Piece {

    private static final double POINT = 0.0;

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = Direction.getKingDirections();

        for (Direction direction : directions) {
            int x = direction.getX();
            int y = direction.getY();
            if (dest.equals(src.move(x, y))) {
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
