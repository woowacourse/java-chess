package chess.domain.piece.type;

import chess.domain.position.Movement;
import chess.util.RouteCalculator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Set;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> getRoute(final Movement movement) {
        if (movement.isDiagonalRightUp()) {
            return RouteCalculator.getRightDiagonalMiddlePositions(movement);
        }

        if (movement.isDiagonalLeftUp()) {
            return RouteCalculator.getLeftDiagonalMiddlePositions(movement);
        }

        if (movement.isVertical()) {
            return RouteCalculator.getVerticalMiddlePositions(movement);
        }

        if (movement.isHorizontal()) {
            return RouteCalculator.getHorizontalMiddlePositions(movement);
        }

        throw new IllegalArgumentException("[ERROR] 전략상 이동할 수 없는 위치입니다.");
    }
}
