package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Set;

public abstract class Pawn extends Piece {
    private final Set<Direction> directions;

    protected Pawn(Position position, Color color, Set<Direction> directions) {
        super(position, color);
        this.directions = directions;
    }

    @Override
    public Set<Position> findPathTo(Position destination) {
        Set<Position> movable = position.findMovablePositions(directions);

        if (!movable.contains(destination)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return position.findCourses(destination);
    }

    public abstract boolean isCaptureMove(Position destination);
}
