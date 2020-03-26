package domain.pieces;

import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;
import domain.team.Team;

public class Pawn extends Piece {
	private static final String INITIAL = "P";

	private final boolean canMoveTwoDistance;

	public Pawn(Team team, Point point) {
		super(INITIAL, team, point);
		canMoveTwoDistance = true;
	}

    public Pawn(Team team, Point point, boolean canMoveTwoDistance) {
        super(INITIAL, team, point);
        this.canMoveTwoDistance = canMoveTwoDistance;
    }

	@Override
	public Piece move(Point afterPoint) {
	    return new Pawn(getTeam(), afterPoint, false);
	}

	@Override
	public void canMove(Direction direction) {
		if (isBlack()) {
			if (direction != Direction.S) {
				throw new CanNotMoveException();
			}
			return;
		}
		if (isWhite()) {
			if (direction != Direction.N) {
				throw new CanNotMoveException();
			}
        }
	}

	@Override
	public void canAttack(Direction direction, Piece other) {
        if (isAlly(other)) {
			throw new CanNotAttackException();
		}

		if (isBlack()) {
			if (direction != Direction.SW && direction != Direction.SE) {
				throw new CanNotAttackException();
			}
            return;
		}
		if (isWhite()) {
			if (direction != Direction.NW && direction != Direction.NE) {
				throw new CanNotAttackException();
			}
        }
	}

	@Override
	public void canReach(Distance distance) {
	    if (canMoveTwoDistance) {
	        if (distance == Distance.TWO_VERTICAL) {
	            return;
            }
        }

		if (distance != Distance.ONE) {
			throw new CanNotReachException();
		}
	}
}
