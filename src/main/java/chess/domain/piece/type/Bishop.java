package chess.domain.piece.type;

import chess.util.RouteCalculator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        return this.position.isDiagonalWith(target);
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        if (this.position.isDiagonalWith(target) // TODO: 오른쪽 대각선, 아래는 왼쪽대각선 의미인데 복잡
                && (this.position.isLeftWith(target) && this.position.isDownWith((target)))
                || (this.position.isRightWith(target) && this.position.isUpWith(target))) {
            return RouteCalculator.getRightDiagonalMiddlePositions(this.position, target);
        }
        if (this.position.isDiagonalWith(target)
                && (this.position.isRightWith(target) && this.position.isDownWith(target))
                || (this.position.isLeftWith(target) && this.position.isUpWith(target))) {
            return RouteCalculator.getLeftDiagonalMiddlePositions(this.position, target);
        }

        return new HashSet<>();
    }
}
