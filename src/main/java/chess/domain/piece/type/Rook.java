package chess.domain.piece.type;

import chess.domain.MultiDirection;
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
        MultiDirection multiDirection = MultiDirection.of(this.position, target);
        return multiDirection == MultiDirection.VERTICAL || multiDirection == MultiDirection.HORIZONTAL;
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        MultiDirection multiDirection = MultiDirection.of(this.position, target);

        if (multiDirection == MultiDirection.VERTICAL) {
            return RouteCalculator.getVerticalMiddlePositions(this.position, target);
        }
        if (multiDirection == MultiDirection.HORIZONTAL) {
            return RouteCalculator.getHorizontalMiddlePositions(this.position, target);
        }

        return new HashSet<>();
    }
}
