package chess.domain.piece;

import java.util.Map;
import java.util.Objects;

import chess.domain.Team;
import chess.domain.position.Position;

public abstract class MovingStrategy {
	public void validateMove(Position source, Position target, Map<Position, Team> teamBoard) {
		checkTarget(source, target, teamBoard);
		checkDirection(source, target);
		checkObstacle(source, target, teamBoard);
	}

	protected void checkTarget(Position source, Position target, Map<Position, Team> teamBoard) {
		Team targetTeam = teamBoard.get(target);
		if (targetTeam == teamBoard.get(source) && Objects.nonNull(targetTeam)) {
			throw new IllegalArgumentException("목적지에 아군이 존재합니다.");
		}
	}

	protected abstract void checkObstacle(Position source, Position target, Map<Position, Team> teamBoard);

	protected abstract void checkDirection(Position source, Position target);
}
