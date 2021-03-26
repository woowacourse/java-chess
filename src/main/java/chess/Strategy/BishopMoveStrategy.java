package chess.Strategy;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.Direction;

import java.util.ArrayList;
import java.util.List;

public class BishopMoveStrategy extends LinearMoveStrategy {
    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.RIGHT_TOP));
        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.LEFT_TOP));
        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.LEFT_BOTTOM));
        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.RIGHT_BOTTOM));

        return movablePositions;
    }

    private List<Position> calculateBishopMovablePositions(Position target, Direction direction) {
        List<Position> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();
        while (isInBorder(horizontalWeight, verticalWeight, direction)) {
            horizontalWeight += direction.getX();
            verticalWeight += direction.getY();
            result.add(
                    Position.of(Horizontal.findFromWeight(horizontalWeight), Vertical.findFromWeight(verticalWeight))
            );
        }
        return result;
    }
}
