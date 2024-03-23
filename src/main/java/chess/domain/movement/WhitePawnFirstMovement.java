package chess.domain.movement;

import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;

public class WhitePawnFirstMovement implements MovementRule {

    @Override
    public boolean isMovable(Position start, Position end, boolean isEnemyExistAtEnd) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);

        return start.isSameRank(Rank.TWO) && rankDifference == 2 && fileDifference == 0;
    }

    @Override
    public List<Position> findPath(Position start, Position end, boolean isEnemyExistAtEnd) {
        if (!isMovable(start, end, isEnemyExistAtEnd)) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }

        Position middle = start.moveToNorth();
        return List.of(middle, end);
    }
}
