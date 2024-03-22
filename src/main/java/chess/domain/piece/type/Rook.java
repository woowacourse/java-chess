package chess.domain.piece.type;

import chess.util.RouteCalculator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        return this.position.isVerticalWith(target) || this.position.isHorizontalWith(target);
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        if (this.position.isVerticalWith(target)) {
            return RouteCalculator.getVerticalMiddlePositions(this.position, target);
        }

        if (this.position.isHorizontalWith(target)) {
            return RouteCalculator.getHorizontalMiddlePositions(this.position, target);
        }

        return new HashSet<>();
    }
}
