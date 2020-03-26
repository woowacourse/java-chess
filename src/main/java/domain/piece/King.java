package domain.piece;

import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class King extends Piece {
	private static final String SYMBOL = "k";

	public King(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected boolean validDirection(Direction direction) {
		return Direction.everyDirection().contains(direction);
	}
}
