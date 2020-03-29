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
				throw new CanNotMoveException();
			}
			return;
		}
		if (isTeam(Team.WHITE)) {
			if (direction != Direction.N) {
				throw new CanNotMoveException();
			}
        }
	}

	@Override
	public void validateAttack(Direction direction, Piece other) {
        if (isAlly(other)) {
			throw new CanNotAttackException();
		}

		if (isTeam(Team.BLACK)) {
			if (direction != Direction.SW && direction != Direction.SE) {
				throw new CanNotAttackException();
			}
            return;
		}
		if (isTeam(Team.WHITE)) {
			if (direction != Direction.NW && direction != Direction.NE) {
				throw new CanNotAttackException();
			}
        }
	}

	@Override
	public void validateReach(Distance distance) {
	    if (canMoveTwoDistance) {
	        if (distance == Distance.VERTICAL_TWO) {
	            return;
            }
        }

		if (distance != Distance.ONE) {
			throw new CanNotReachException();
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


