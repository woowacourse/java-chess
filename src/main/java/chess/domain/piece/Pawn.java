package chess.domain.piece;

import chess.domain.Point;

import static chess.domain.ChessGame.BLACK;
import static chess.domain.ChessGame.WHITE;

public class Pawn extends Piece {
    public Pawn(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public boolean isMovableRoute(Piece target) {
        //TODO: 폰은 직진만 가능
        //TODO: 폰은 시작 때만 두 칸 이동 가능

        Direction direction = Direction.findDirection(this.point, target.point);
        if (this.color.equals(WHITE) && !direction.equals(Direction.NORTH) && !direction.equals(Direction.NORTH_EAST) && !direction.equals(Direction.NORTH_WEST)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        if (this.color.equals(BLACK) && !direction.equals(Direction.SOUTH) && !direction.equals(Direction.SOUTH_EAST) && !direction.equals(Direction.SOUTH_WEST)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }

        int distance = this.point.calculateDistance(target.point);
        if (distance == 1 && target instanceof Empty) {
            return true;
        }
        if (distance == 2 && !(target instanceof Empty)) {
            return true;
        }
        if (distance == 4 && target instanceof Empty && ((this.color.equals(BLACK) && this.point.getRow() == 1) || (this.color.equals(WHITE) && this.point.getRow() == 6))) {
            return true;
        }
        throw new IllegalArgumentException(Piece.IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
    }

    @Override
    public Point moveOneStep(Point target) {
        return target;
    }
}
