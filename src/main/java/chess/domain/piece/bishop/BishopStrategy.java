package chess.domain.piece.bishop;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.piece.MovingStrategy;
import chess.domain.position.Position;

public class BishopStrategy extends MovingStrategy {
	private static final List<Direction> directions = Direction.DIAGONAL_DIRECTION;

	@Override
	protected void checkDirection(Position source, Position target) {
		Direction direction = Direction.getDirection(source, target);
		if (!directions.contains(direction)) {
			throw new IllegalArgumentException("해당 방향으로 이동할 수 없습니다.");
		}
	}

	@Override
	protected void checkObstacle(Position source, Position target, Map<Position, Team> teamBoard) {
		Direction direction = Direction.getDirection(source, target);
		Position pathPosition = source.plusDirection(direction);
		while (!pathPosition.equals(target)) {
			checkObstacleOnPath(teamBoard, pathPosition);
			pathPosition = pathPosition.plusDirection(direction);
		}
	}

	private void checkObstacleOnPath(Map<Position, Team> teamBoard, Position pathPosition) {
		if (!Objects.isNull(teamBoard.get(pathPosition))) {
			throw new IllegalArgumentException("해당 방향에 장애물이 존재합니다.");
		}
	}
}
