package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.MovingOrder;
import chess.domain.piece.Color;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    @Override
    public void movePossibility(Color color, MovingOrder movingOrder) {

        List<Direction> directions = Direction.pawnDirection(color);

        if (movingOrder.isInitLineOfPawn(color)) {
            if (!isSameDirectionIfInitLine(movingOrder, directions)) {
                throw new IllegalArgumentException("첫라인일떄 에러");
            }
            return;
        }

        if (!isSameDirectionOfDirections(movingOrder, directions)) {
            throw new IllegalArgumentException("이외에러");
        }
    }

    private boolean isSameDirectionIfInitLine(MovingOrder movingOrder, List<Direction> directions) {
        return movingOrder.isPawnInitDirectionOf(directions.get(0)) ||
                isSameDirectionOfDirections(movingOrder, directions.subList(1, directions.size()));
    }

    private boolean isSameDirectionOfDirections(MovingOrder movingOrder, List<Direction> directions) {
        return directions.stream()
                .anyMatch(movingOrder::isSameDirection);
    }
}
