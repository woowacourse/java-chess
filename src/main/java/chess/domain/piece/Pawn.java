package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public abstract class Pawn extends Piece {
    private final Set<Direction> directions;

    protected Pawn(Position position, Color color, Set<Direction> directions) {
        super(position, color);
        this.directions = directions;
    }

    @Override
    public Set<Position> findMovablePositions(Position destination) {
        Set<Position> movable = position.findMovablePositions(directions);

        if (!movable.contains(destination)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return position.findCourses(position.findDirectionTo(destination), destination);
    }

    public abstract boolean isCaptureMove(Position destination);
}
