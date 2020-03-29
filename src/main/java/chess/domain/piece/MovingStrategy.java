package chess.domain.piece;

import java.util.Map;
import java.util.Objects;

import chess.domain.Team;
import chess.domain.position.Position;

public abstract class MovingStrategy {
	public void validateMove(Position source, Position target, Map<Position, Team> boardDto) {
		checkTarget(source, target, boardDto);
		checkDirection(source, target);
		checkObstacle(source, target, boardDto);
	}

	protected void checkTarget(Position source, Position target, Map<Position, Team> boardDto) {
		Team targetTeam = boardDto.get(target);
		if (targetTeam == boardDto.get(source) && Objects.nonNull(targetTeam)) {
			throw new IllegalArgumentException("목적지에 아군이 존재합니다.");
		}
	}

	protected abstract void checkObstacle(Position source, Position target, Map<Position, Team> boardDto);

	protected abstract void checkDirection(Position source, Position target);
}
