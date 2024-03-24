package chess.domain.piece.type;

import chess.domain.MultiDirection;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.util.RouteCalculator;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {
    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public boolean canMoveTo(final Position source, final Position target) {
        MultiDirection multiDirection = MultiDirection.of(source, target);
        return multiDirection == MultiDirection.RIGHT_DIAGONAL || multiDirection == MultiDirection.LEFT_DIAGONAL;
    }

    @Override
    public Set<Position> getRoute(final Position source, final Position target) {
        MultiDirection multiDirection = MultiDirection.of(source, target);

        if (multiDirection == MultiDirection.RIGHT_DIAGONAL) {
            return RouteCalculator.getRightDiagonalMiddlePositions(source, target);
        }
        if (multiDirection == MultiDirection.LEFT_DIAGONAL) {
            return RouteCalculator.getLeftDiagonalMiddlePositions(source, target);
        }

        return new HashSet<>();
    }
}
