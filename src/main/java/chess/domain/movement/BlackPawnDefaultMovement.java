package chess.domain.movement;

import chess.domain.position.Position;
import java.util.List;

public class BlackPawnDefaultMovement implements MovementRule{

    @Override
    public boolean isMovable(Position start, Position end) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);

        return rankDifference == -1 && fileDifference == 0;
    }

    @Override
    public List<Position> findPath(Position start, Position end) {
        return List.of(end);
    }
}
