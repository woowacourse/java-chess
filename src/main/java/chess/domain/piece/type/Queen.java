package chess.domain.piece.type;

import chess.domain.MultiDirection;
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
        MultiDirection multiDirection = MultiDirection.of(this.position, target);

        return multiDirection == MultiDirection.VERTICAL || multiDirection == MultiDirection.HORIZONTAL
                || multiDirection == MultiDirection.LEFT_DIAGONAL || multiDirection == MultiDirection.RIGHT_DIAGONAL;
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        MultiDirection multiDirection = MultiDirection.of(this.position, target);

        if (multiDirection == MultiDirection.RIGHT_DIAGONAL) {
            return RouteCalculator.getRightDiagonalMiddlePositions(this.position, target);
        }
        if (multiDirection == MultiDirection.LEFT_DIAGONAL) {
            return RouteCalculator.getLeftDiagonalMiddlePositions(this.position, target);
        }
        if (multiDirection == MultiDirection.VERTICAL) {
            return RouteCalculator.getVerticalMiddlePositions(this.position, target);
        }
        if (multiDirection == MultiDirection.HORIZONTAL) {
            return RouteCalculator.getHorizontalMiddlePositions(this.position, target);
        }
        return new HashSet<>();
    }
}
