package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public class Queen extends Piece {
    private static Set<Direction> directions = Direction.getEightDirection();

    public Queen(Position position, Color color) {
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
    public Queen update(Position destination) {
        return new Queen(destination, color);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_QUEEN;
        }
        return PieceType.BLACK_QUEEN;
    }
}
