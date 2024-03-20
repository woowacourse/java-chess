package chess.domain;

import java.util.Set;

public class Bishop extends Piece {
    private static Set<Direction> directions = Direction.getDiagonalDirection();

    public Bishop(Position position, Color color) {
        super(position, color);
    }

    @Override
    public Set<Position> findMovablePositions(Position destination) {
        Direction direction = position.findDirectionTo(destination);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return position.findCourses(direction, destination);
    }

    @Override
    public Bishop update(Position destination) {
        return new Bishop(destination, color);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_BISHOP;
        }
        return PieceType.BLACK_BISHOP;
    }
}
