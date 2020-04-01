package chess.domain.piece.knight;

import java.util.List;
import java.util.Map;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.piece.MovingStrategy;
import chess.domain.position.Position;

public class KnightStrategy extends MovingStrategy {
	private static final List<Direction> directions = Direction.knightDirection();

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
		if (!pathPosition.equals(target)) {
			throw new IllegalArgumentException("도달할 수 없는 거리입니다.");
		}
	}
}
