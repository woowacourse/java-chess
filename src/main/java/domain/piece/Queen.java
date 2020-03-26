package domain.piece;

import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Queen extends Piece {
	private static final String SYMBOL = "q";

	public Queen(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected boolean validDirection(Direction direction) {
		return Direction.diagonalDirection().contains(direction) ||
			Direction.linearDirection().contains(direction);
	}

	@Override
	boolean validStepSize(int rowGap, int columnGap) {
		return true;
	}
}
