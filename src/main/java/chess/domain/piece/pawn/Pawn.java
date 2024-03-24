package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Set;

public abstract class Pawn extends Piece {
    private final Set<Direction> directions;

    protected Pawn(final Position position, final Color color, final Set<Direction> directions) {
        super(position, color);
        this.directions = directions;
    }

    @Override
    public Set<Position> findPathTo(final Position destination) {
        final Set<Position> path = position.findPathTo(directions);

        if (!path.contains(destination)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return position.findPath(destination);
    }

    public abstract boolean isCaptureMove(final Position destination);
}
