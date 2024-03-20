package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public abstract class SlidingPiece extends Piece {
    private final Set<Direction> directions;

    public SlidingPiece(Position position, Color color, Set<Direction> directions) {
        super(position, color);
        this.directions = directions;
    }

    public Set<Position> findMovablePositions(Position destination) {
        Direction direction = position.findDirectionTo(destination);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return position.findCourses(direction, destination);
    }

    public abstract PieceType pieceType();
}
