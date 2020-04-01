package chess.domain.piece.pawn;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.piece.MovingStrategy;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class PawnStrategy extends MovingStrategy {
	protected final List<Direction> directions;
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
	protected void checkObstacle(Position source, Position target, Map<Position, Team> teamBoard) {
		Direction direction = Direction.getDirection(source, target);
		checkPawnRange(source, target, direction);

		if (target.minusColumn(source) != 0) {  //진행방향이 대각선인 경우
			checkDiagonalEmpty(target, teamBoard);
		}
		if (target.minusColumn(source) == 0) {  //진행방향이 직선인 경우
			checkEmpty(target, teamBoard);
		}
	}

	private void checkPawnRange(Position source, Position target, Direction direction) {
		if (source.getRow() == Row.SIX || source.getRow() == Row.TWO) {
			checkNotMovedRange(source, direction, target);
		} else {
			Position pathPosition = source.plusDirection(direction);
			checkRange(target, pathPosition);
		}
	}

	private void checkNotMovedRange(Position source, Direction direction, Position target) {
		Position position = source.plusDirection(direction);
		if (position.equals(target))
			return;
		position = position.plusDirection(direction);
		if (!position.equals(target)) {
			throw new IllegalArgumentException("도달할 수 없는 거리입니다.");
		}
	}

	private void checkEmpty(Position target, Map<Position, Team> teamBoard) {
		Team targetTeam = teamBoard.get(target);
		if (Objects.nonNull(targetTeam) && targetTeam.isNotSameTeam(this.team)) {
			throw new IllegalArgumentException("Pawn은 대각선으로만 공격할 수 있습니다.");
		}
	}

	private void checkDiagonalEmpty(Position target, Map<Position, Team> teamBoard) {
		if (Objects.isNull(teamBoard.get(target))) {
			throw new IllegalArgumentException("적군이 없는 곳이라면 Pawn은 대각선으로 이동할 수 없습니다.");
		}
	}

	private void checkRange(Position target, Position pathPosition) {
		if (!pathPosition.equals(target)) {
			throw new IllegalArgumentException("도달할 수 없는 거리입니다.");
		}
	}
}
