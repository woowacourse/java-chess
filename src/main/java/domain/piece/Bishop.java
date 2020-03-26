package domain.piece;

import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Bishop extends Piece {
	private static final String SYMBOL = "b";

	public Bishop(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected boolean validDirection(Direction direction) {
		return Direction.diagonalDirection().contains(direction);
	}
}
