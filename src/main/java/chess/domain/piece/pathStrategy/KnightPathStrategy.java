package chess.domain.piece.pathStrategy;

import chess.domain.board.Position;
import chess.exception.NotMovableException;

import java.util.ArrayList;
import java.util.List;

import static chess.util.NullValidator.validateNull;

public class KnightPathStrategy implements PathStrategy {
    private static final int MOVABLE_DISTANCE_SUM = 3;

    @Override
    public void validateDistance(Position sourcePosition, Position targetPosition) {
        validateNull(sourcePosition, targetPosition);

        int gapSum = sourcePosition.getXYGapSum(targetPosition);
        if (gapSum != MOVABLE_DISTANCE_SUM) {
            throw new NotMovableException(String.format("지정한 위치 %s는 나이트가 이동할 수 없는 곳입니다.", targetPosition.getName()));
        }
    }

    @Override
    public List<Position> createPath(Position sourcePosition, Position targetPosition) {
        validateNull(targetPosition);

        List<Position> path = new ArrayList<>();
        path.add(targetPosition);

        return path;
    }
}
