package chess.domain.piece.strategy;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;

public class KingStrategy extends MovingStrategy{
    private static final List directions = Direction.everyDirection();

    @Override
    protected void checkDirection(Position source, Position target) {
        Direction direction = Direction.getDirection(source, target);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("해당 방향으로 이동할 수 없습니다.");
        }
    }

    @Override
    protected void checkObstacle(Position source, Position target, Map<Position, Team> boardDto) {
        Direction direction = Direction.getDirection(source, target);
        Position pathPosition = source.plusDirection(direction);
        if (!pathPosition.equals(target)) {
            throw new IllegalArgumentException("도달할 수 없는 거리입니다.");
        }
    }
}
