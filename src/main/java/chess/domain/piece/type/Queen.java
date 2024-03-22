package chess.domain.piece.type;

import chess.RouteCalculator;
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
        return this.position.isVerticalWith(target)
                || this.position.isHorizontalWith(target)
                || this.position.isDiagonalWith(target);
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        if (this.position.isRightDiagonalWith(target)) {
            return this.position.getRightDiagonalMiddlePositions(target);
        }
        if (this.position.isLeftDiagonalWith(target)) {
            return this.position.getLeftDiagonalMiddlePositions(target);
        }
        if (this.position.isVerticalWith(target)) {
            return RouteCalculator.getVerticalMiddlePositions(this.position, target);
        }
        if (this.position.isHorizontalWith(target)) {
            return RouteCalculator.getHorizontalMiddlePositions(this.position, target);
        }
        return new HashSet<>();
    }
}
