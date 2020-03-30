package chess.domain.piece.pawn;

import java.util.Map;
import java.util.Objects;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;

public class NotMovedPawnStrategy extends PawnStrategy {
	public NotMovedPawnStrategy(Team team) {
		super(team);
	}

	@Override
	protected void checkObstacle(Position source, Position target, Map<Position, Team> teamBoard) {
		Direction direction = Direction.getDirection(source, target);
		int columnDifference = Math.abs(target.minusColumn(source));
		Position pathPosition = source.plusDirection(direction);

		if (columnDifference != 0) {  //진행방향이 대각선인 경우
			diagonalDirectionCheck(target, teamBoard, pathPosition);
		}
		if (columnDifference == 0) {  //진행방향이 직선인 경우
			checkLinearDirection(source, target, teamBoard, pathPosition, direction);
		}
	}

	private void checkLinearDirection(Position source, Position target, Map<Position, Team> teamBoard,
		Position pathPosition, Direction direction) {

		checkEmptyPlace(target, teamBoard);
		checkLinearRange(source, target);
		checkObstacle(teamBoard, pathPosition);
		pathPosition = pathPosition.plusDirection(direction);
		checkObstacle(teamBoard, pathPosition);
	}

	private void checkEmptyPlace(Position target, Map<Position, Team> teamBoard) {
		Team targetTeam = teamBoard.get(target);
		if (Objects.nonNull(targetTeam) && targetTeam.isNotSameTeam(this.team)) {
			throw new IllegalArgumentException("Pawn은 대각선으로만 공격할 수 있습니다.");
		}
	}

	private void checkObstacle(Map<Position, Team> teamBoard, Position pathPosition) {
		if (Objects.nonNull(teamBoard.get(pathPosition))) {
			throw new IllegalArgumentException("해당 방향에 장애물이 존재합니다.");
		}
	}

	private void checkLinearRange(Position source, Position target) {
		int rowDifference = Math.abs(source.minusRow(target));
		if (rowDifference > 2) {
			throw new IllegalArgumentException("이동할 수 없는 거리입니다.");
		}
	}

	private void diagonalDirectionCheck(Position target, Map<Position, Team> teamBoard, Position pathPosition) {
		checkNoEnemyToTarget(target, teamBoard);
		checkOverRange(target, pathPosition);
	}

	private void checkOverRange(Position target, Position pathPosition) {
		if (!pathPosition.equals(target)) {
			throw new IllegalArgumentException("이동할 수 없는 거리입니다.");
		}
	}

	private void checkNoEnemyToTarget(Position target, Map<Position, Team> teamBoard) {
		if (Objects.isNull(teamBoard.get(target))) {
			throw new IllegalArgumentException("적군이 없는 곳이라면 Pawn은 대각선으로 이동할 수 없습니다.");
		}
	}
}
