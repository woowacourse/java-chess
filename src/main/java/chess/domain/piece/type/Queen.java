package chess.domain.piece.type;

import chess.domain.Path;
import chess.util.RouteCalculator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        Path path = Path.of(this.position, target);

        return path == Path.VERTICAL || path == Path.HORIZONTAL
                || path == Path.LEFT_DIAGONAL || path == Path.RIGHT_DIAGONAL;
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
        if (path == Path.VERTICAL) {
            return RouteCalculator.getVerticalMiddlePositions(this.position, target);
        }
        if (path == Path.HORIZONTAL) {
            return RouteCalculator.getHorizontalMiddlePositions(this.position, target);
        }
        return new HashSet<>();
    }
}
