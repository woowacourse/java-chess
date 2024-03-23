package chess.domain.piece.type;

import chess.domain.Path;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.util.RouteCalculator;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        Path path = Path.of(this.position, target);
        return path == Path.VERTICAL || path == Path.HORIZONTAL;
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        Path path = Path.of(this.position, target);

        if (path == Path.VERTICAL) {
            return RouteCalculator.getVerticalMiddlePositions(this.position, target);
        }
        if (path == Path.HORIZONTAL) {
            return RouteCalculator.getHorizontalMiddlePositions(this.position, target);
        }

        return new HashSet<>();
    }
}
