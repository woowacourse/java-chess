package domain.pieces;

import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;
import domain.team.Team;

import java.util.Objects;

public class Pawn extends Piece {
	public static final double SCORE = 1;
	private static final int TWO = 2;
	public static final double HALF_SCORE = SCORE / TWO;
	private static final String INITIAL = "P";

	private final boolean canMoveTwoDistance;

	public Pawn(Team team, Point point) {
		super(INITIAL, team, point, SCORE);
		canMoveTwoDistance = true;
	}

	public Pawn(Team team, Point point, boolean canMoveTwoDistance) {
		super(INITIAL, team, point, SCORE);
		this.canMoveTwoDistance = canMoveTwoDistance;
	}

	@Override
	public Piece move(Point afterPoint) {
		return new Pawn(getTeam(), afterPoint, false);
	}

	@Override
	public void validateMoveDirection(Direction direction) {
		if (isTeam(Team.BLACK)) {
			if (direction != Direction.S) {
				throw new CanNotMoveException("흑색 팀 Pawn은 아래로만 움직일 수 있습니다.");
			}
			return;
		}
		if (isTeam(Team.WHITE)) {
			if (direction != Direction.N) {
				throw new CanNotMoveException("백색 팀 Pawn은 위로만 움직일 수 있습니다.");
			}
		}
	}

	@Override
	public void validateAttack(Direction direction, Piece other) {
		if (isAlly(other)) {
			throw new CanNotAttackException("아군을 공격할 수 없습니다.");
		}

		if (isTeam(Team.BLACK)) {
			validateIsDiagonalDown(direction);
			return;
		}
		if (isTeam(Team.WHITE)) {
			validateIsDiagonalUp(direction);
		}
	}

	private void validateIsDiagonalDown(Direction direction) {
		if (direction.isNotDiagonalDown()) {
			throw new CanNotAttackException("흑색 팀 폰은 대각선 아래로만 공격할 수 있습니다.");
		}
	}

	private void validateIsDiagonalUp(Direction direction) {
		if (direction.isNotDiagonalUp()) {
			throw new CanNotAttackException("백색 팀 폰은 대각선 위로만 공격할 수 있습니다.");
		}
	}

	@Override
	public void validateReach(Distance distance) {
		if (distance == Distance.VERTICAL_TWO && canMoveTwoDistance) {
			return;
		}

		if (distance != Distance.ONE) {
			throw new CanNotReachException("한 번 이상 움직인 폰은 한칸만 움직일 수 있습니다."
					+ System.lineSeparator()
					+ "단, 처음엔 두 칸 앞으로 갈 수 있습니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Pawn pawn = (Pawn) o;
		return canMoveTwoDistance == pawn.canMoveTwoDistance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), canMoveTwoDistance);
	}
}


