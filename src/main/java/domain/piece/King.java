package domain.piece;

import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class King extends Piece {
	private static final String SYMBOL = "k";
	private static final int MAX_STEP_SIZE = 2;

	public King(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected boolean validDirection(Direction direction) {
		return Direction.everyDirection().contains(direction);
	}

	@Override
	boolean validStepSize(int rowGap, int columnGap) {
		return (Math.abs(rowGap) < MAX_STEP_SIZE) && (Math.abs(columnGap) < MAX_STEP_SIZE);
	}
}
