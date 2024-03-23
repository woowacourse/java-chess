package chess.domain.piece.type;

import chess.domain.Movement;
import chess.util.RouteCalculator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Set;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> getRoute(final Movement movement) {
        if (movement.isVertical()) {
            return RouteCalculator.getVerticalMiddlePositions(movement);
        }

        if (movement.isHorizontal()) {
            return RouteCalculator.getHorizontalMiddlePositions(movement);
        }

        throw new IllegalArgumentException("[ERROR] 전략상 이동할 수 없는 위치입니다.");
    }
}
