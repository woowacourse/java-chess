package chess.domain.piece.pathStrategy;

import chess.domain.board.Position;
import chess.domain.piece.direction.Direction;
import chess.exception.NotMovableException;

import java.util.ArrayList;
import java.util.List;

import static chess.util.NullValidator.validateNull;

public class RookPathStrategy implements PathStrategy {

    @Override
    public void validateDistance(Position sourcePosition, Position targetPosition) {
        validateNull(sourcePosition, targetPosition);

        if (sourcePosition.isDifferentXPoint(targetPosition) && sourcePosition.isDifferentYPoint(targetPosition)) {
            throw new NotMovableException(String.format("지정한 위치 %s는 룩이 이동할 수 없는 곳입니다.", targetPosition.getName()));
        }
    }

    @Override
    public List<Position> createPath(Position sourcePosition, Position targetPosition) {
        validateNull(sourcePosition, targetPosition);

        List<Position> path = new ArrayList<>();
        Direction direction = getDirection(sourcePosition, targetPosition);

        Position currentPosition = sourcePosition;
        while (!currentPosition.equals(targetPosition)) {
            Position changePosition = currentPosition.changeTo(direction);
            currentPosition = changePosition;
            path.add(changePosition);
        }

        return path;
    }

    private Direction getDirection(Position sourcePosition, Position targetPosition) {
        int xPointDirectionValue = sourcePosition.getXPointDirectionValueTo(targetPosition);
        int yPointDirectionValue = sourcePosition.getYPointDirectionValueTo(targetPosition);
        return Direction.of(xPointDirectionValue, yPointDirectionValue);
    }
}
