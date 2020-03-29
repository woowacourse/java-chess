package domain.piece;

import java.util.List;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class King extends Piece {
	private static final String SYMBOL = "k";
	private static final int MAX_STEP_SIZE = 2;
	private static final double SCORE = 0;

	public King(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected boolean validDirection(Direction direction) {
		if (Direction.everyDirection().contains(direction)) {
			return true;
		}
		throw new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION);
	}

	@Override
	protected boolean validStepSize(int rowGap, int columnGap) {
		if ((Math.abs(rowGap) < MAX_STEP_SIZE) && (Math.abs(columnGap) < MAX_STEP_SIZE)) {
			return true;
		}
		throw new InvalidPositionException(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@Override
	protected boolean validateRoute(Direction direction, Position targetPosition, List<Rank> ranks) {
		return true;
	}

	@Override
	protected String getSymbol() {
		return SYMBOL;
	}

	@Override
	public double getScore() {
		return SCORE;
	}
}
