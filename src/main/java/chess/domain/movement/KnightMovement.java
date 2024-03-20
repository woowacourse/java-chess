package chess.domain.movement;

import chess.domain.position.Position;
import java.util.List;

public class KnightMovement implements MovementRule {

    @Override
    public boolean isMovable(Position start, Position end) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);
        int rankDifferenceSize = Math.abs(rankDifference);
        int fileDifferenceSize = Math.abs(fileDifference);

        return (rankDifferenceSize == 2 && fileDifferenceSize == 1)
                || (fileDifferenceSize == 2 && rankDifferenceSize == 1);
    }

    @Override
    public List<Position> findPath(Position start, Position end) {
        return List.of(end);
    }
}
