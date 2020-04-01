package domain.piece;

import java.util.List;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class King extends Piece {
	private static final String SYMBOL = "k";
	private static final int MAX_STEP_SIZE = 1;
	private static final double SCORE = 0;

	public King(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected void validateDirection(Direction direction) {
		if (direction.isNotContain(Direction.everyDirection())) {
			throw new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION);
		}
	}

	@Override
	protected void validateStepSize(Position sourcePosition, Position targetPosition) {
		int rowGap = this.position.calculateRowGap(targetPosition);
		int columnGap = this.position.calculateColumnGap(targetPosition);

		if ((Math.abs(rowGap) > MAX_STEP_SIZE) || (Math.abs(columnGap) > MAX_STEP_SIZE)) {
			throw new InvalidPositionException(InvalidPositionException.INVALID_STEP_SIZE);
		}
	}

	@Override
	protected void validateRoute(Direction direction, Position targetPosition, List<Rank> ranks) {
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
