package chess.domain.piece.type;

import chess.domain.MultiDirection;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.util.RouteCalculator;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    public Rook(final Color color) {
        super(color);
    }

    @Override
    public boolean canMoveTo(final Position source, final Position target) {
        MultiDirection multiDirection = MultiDirection.of(source, target);
        return multiDirection == MultiDirection.VERTICAL || multiDirection == MultiDirection.HORIZONTAL;
    }

    @Override
    public Set<Position> getRoute(final Position source, final Position target) {
        MultiDirection multiDirection = MultiDirection.of(source, target);

        if (multiDirection == MultiDirection.VERTICAL) {
            return RouteCalculator.getVerticalMiddlePositions(source, target);
        }
        if (multiDirection == MultiDirection.HORIZONTAL) {
            return RouteCalculator.getHorizontalMiddlePositions(source, target);
        }

        return new HashSet<>();
    }
}
