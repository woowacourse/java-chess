package chess.domain.piece.pawn;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.piece.MovingStrategy;
import chess.domain.position.Position;

public class PawnStrategy extends MovingStrategy {
	protected final List directions;

	protected final Team team;

	public PawnStrategy(Team team) {
		this.team = team;
		this.directions = Direction.findPawnDirectionBy(team);
	}

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

		if (target.minusColumn(source) != 0) {
			if (Objects.isNull(boardDto.get(target))) {
				throw new IllegalArgumentException("적군이 없는 곳이라면 Pawn은 대각선으로 이동할 수 없습니다.");
			}
		}
		if (target.minusColumn(source) == 0) {
			Team targetTeam = boardDto.get(target);
			if (Objects.nonNull(targetTeam) && targetTeam.isNotSameTeam(this.team)) {
				throw new IllegalArgumentException("Pawn은 대각선으로만 공격할 수 있습니다.");
			}
		}
	}
}
