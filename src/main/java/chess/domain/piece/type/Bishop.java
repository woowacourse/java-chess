package chess.domain.piece.type;

import chess.domain.Path;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.util.RouteCalculator;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        Path path = Path.of(this.position, target);
        return path == Path.RIGHT_DIAGONAL || path == Path.LEFT_DIAGONAL;
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        Path path = Path.of(this.position, target);

        if (path == Path.RIGHT_DIAGONAL) {
            return RouteCalculator.getRightDiagonalMiddlePositions(this.position, target);
        }
        if (path == Path.LEFT_DIAGONAL) {
            return RouteCalculator.getLeftDiagonalMiddlePositions(this.position, target);
        }

        return new HashSet<>();
    }
}
