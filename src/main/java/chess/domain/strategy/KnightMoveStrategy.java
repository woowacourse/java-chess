package chess.domain.strategy;

import chess.domain.Position;
import chess.domain.PositionDifference;
import java.util.List;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(Position currentPosition, Position newPosition) {
        PositionDifference positionDifference = currentPosition.calculateDifference(newPosition);

        List<PositionDifference> knightPositionDifferences = List.of(
                new PositionDifference(2, 1), new PositionDifference(-2, 1),
                new PositionDifference(2, -1), new PositionDifference(-2, -1),
                new PositionDifference(1, 2), new PositionDifference(-1, 2),
                new PositionDifference(1, -2), new PositionDifference(-1, -2)
        );

        return knightPositionDifferences.contains(positionDifference);
    }
}
