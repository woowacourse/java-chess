package domain.piece;

import java.util.List;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Knight extends Piece {
	private static final String SYMBOL = "n";
	private static final double SCORE = 2.5;

	public Knight(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected void validateDirection(Direction direction) {
		if (direction.isNotContain(Direction.knightDirection())) {
			throw new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION);
		}
	}

	@Override
	protected void validateStepSize(Position sourcePosition, Position targetPosition) {
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
