package chess.domain.strategy;

import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.PositionDifference;
import java.util.List;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(final Position currentPosition, final Position newPosition) {
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
