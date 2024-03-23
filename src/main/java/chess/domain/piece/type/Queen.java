package chess.domain.piece.type;

import chess.domain.MultiDirection;
import chess.util.RouteCalculator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
    public Queen(final Color color) {
        super(color);
    }

//    public Queen(final Color color, final Position position) {
//        super(color, position);
//    }
//
//    @Override
//    public boolean canMoveTo(final Position target) {
//        MultiDirection multiDirection = MultiDirection.of(source, target);
//
//        return multiDirection == MultiDirection.VERTICAL || multiDirection == MultiDirection.HORIZONTAL
//                || multiDirection == MultiDirection.LEFT_DIAGONAL || multiDirection == MultiDirection.RIGHT_DIAGONAL;
//    }
//
//    @Override
//    public Set<Position> getRoute(final Position target) {
//        MultiDirection multiDirection = MultiDirection.of(source, target);
//
//        if (multiDirection == MultiDirection.RIGHT_DIAGONAL) {
//            return RouteCalculator.getRightDiagonalMiddlePositions(source, target);
//        }
//        if (multiDirection == MultiDirection.LEFT_DIAGONAL) {
//            return RouteCalculator.getLeftDiagonalMiddlePositions(source, target);
//        }
//        if (multiDirection == MultiDirection.VERTICAL) {
//            return RouteCalculator.getVerticalMiddlePositions(source, target);
//        }
//        if (multiDirection == MultiDirection.HORIZONTAL) {
//            return RouteCalculator.getHorizontalMiddlePositions(source, target);
//        }
//        return new HashSet<>();
//    }

    @Override
    public boolean canMoveTo(final Position source, final Position target) {
        MultiDirection multiDirection = MultiDirection.of(source, target);

        return multiDirection == MultiDirection.VERTICAL || multiDirection == MultiDirection.HORIZONTAL
                || multiDirection == MultiDirection.LEFT_DIAGONAL || multiDirection == MultiDirection.RIGHT_DIAGONAL;

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
        if (multiDirection == MultiDirection.VERTICAL) {
            return RouteCalculator.getVerticalMiddlePositions(source, target);
        }
        if (multiDirection == MultiDirection.HORIZONTAL) {
            return RouteCalculator.getHorizontalMiddlePositions(source, target);
        }
        return new HashSet<>();
    }
}
