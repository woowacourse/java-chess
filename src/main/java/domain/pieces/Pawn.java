package domain.pieces;

import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.coordinate.Direction;
import domain.coordinate.Distance;
import domain.coordinate.Coordinate;
import domain.coordinate.Row;
import domain.team.Team;

public class Pawn extends Piece {
	public Pawn(Team team, Coordinate coordinate) {
		super(PieceType.PAWN, team, coordinate);
	}

	@Override
	public Piece move(Coordinate afterCoordinate) {
		return new Pawn(getTeam(), afterCoordinate);
	}

	@Override
	public void validateMoveDirection(Direction direction) {
		if (isTeam(Team.BLACK)) {
			validateIsSouth(direction);
			return;
		}
		if (isTeam(Team.WHITE)) {
			validateIsNorth(direction);
		}
	}

	private void validateIsSouth(Direction direction) {
		if (direction != Direction.SOUTH) {
			throw new CanNotMoveException("흑색 팀 Pawn은 아래로만 움직일 수 있습니다.");
		}
	}

	private void validateIsNorth(Direction direction) {
		if (direction != Direction.NORTH) {
			throw new CanNotMoveException("백색 팀 Pawn은 위로만 움직일 수 있습니다.");
		}
	}

	@Override
	public void validateAttack(Direction direction, Piece other) {
		if (isAlly(other)) {
			throw new CanNotAttackException("아군을 공격할 수 없습니다.");
		}

		if (isTeam(Team.BLACK)) {
			validateIsDiagonalSouth(direction);
			return;
		}
		if (isTeam(Team.WHITE)) {
			validateIsDiagonalNorth(direction);
		}
	}

	private void validateIsDiagonalSouth(Direction direction) {
		if (direction.isNotDiagonalDown()) {
			throw new CanNotAttackException("흑색 팀 폰은 대각선 아래로만 공격할 수 있습니다.");
		}
	}

	private void validateIsDiagonalNorth(Direction direction) {
		if (direction.isNotDiagonalUp()) {
			throw new CanNotAttackException("백색 팀 폰은 대각선 위로만 공격할 수 있습니다.");
		}
	}

	@Override
	public void validateReach(Distance distance) {
		if (distance == Distance.VERTICAL_TWO && getCoordinate().matchRow(Row.TWO, Row.SEVEN)) {
			return;
		}

		if (distance != Distance.ONE) {
			throw new CanNotReachException("한 번 이상 움직인 폰은 한칸만 움직일 수 있습니다."
					+ System.lineSeparator()
					+ "단, 처음엔 두 칸 앞으로 갈 수 있습니다.");
		}
	}
}


