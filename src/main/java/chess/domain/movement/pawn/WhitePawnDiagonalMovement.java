package chess.domain.movement.pawn;

import chess.domain.movement.MovementRule;
import chess.domain.position.Position;
import java.util.List;

public class WhitePawnDiagonalMovement implements MovementRule {

    public boolean isMovable(Position start, Position end, boolean hasEnemy) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);
        int fileDifferenceSize = Math.abs(fileDifference);

        return hasEnemy && rankDifference == 1 && fileDifferenceSize == 1;
    }

    public List<Position> findPath(Position start, Position end, boolean hasEnemy) {
        return List.of();
    }
}
