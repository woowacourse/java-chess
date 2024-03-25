package chess.domain.movement.pawn;

import chess.domain.movement.MovementRule;
import chess.domain.position.Position;
import java.util.List;

public final class BlackPawnDefaultMovement implements MovementRule {

    public boolean isMovable(Position start, Position end, boolean hasEnemy) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);

        return !hasEnemy && rankDifference == -1 && fileDifference == 0;
    }

    public List<Position> findPath(Position start, Position end, boolean hasEnemy) {
        if (!isMovable(start, end, hasEnemy)) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }

        return List.of(end);
    }
}
