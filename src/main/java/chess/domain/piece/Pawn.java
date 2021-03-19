package chess.domain.piece;

import chess.domain.Point;

import java.util.Optional;

import static chess.domain.ChessGame.BLACK;
import static chess.domain.ChessGame.WHITE;

public class Pawn extends Piece {
    public Pawn(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece target) {
        Direction direction = Direction.findDirection(this.point, target.point);
        if (this.color.equals(WHITE) && !direction.equals(Direction.NORTH) && !direction.equals(Direction.NORTH_EAST) && !direction.equals(Direction.NORTH_WEST)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        if (this.color.equals(BLACK) && !direction.equals(Direction.SOUTH) && !direction.equals(Direction.SOUTH_EAST) && !direction.equals(Direction.SOUTH_WEST)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }

        int distance = this.point.calculateDistance(target.point);
        if (distance == 1 && target instanceof Empty) {
            return Optional.of(direction);
        }
        if (distance == 2 && !(target instanceof Empty)) {
            return Optional.of(direction);
        }
        if (distance == 4 && target instanceof Empty && ((this.color.equals(BLACK) && this.point.getRow() == 1) || (this.color.equals(WHITE) && this.point.getRow() == 6))) {
            return Optional.of(direction);
        }
        throw new IllegalArgumentException(Piece.IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return target;
    }
}
