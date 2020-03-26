package domain.piece;

import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Pawn extends Piece {
	private static final String SYMBOL = "p";

	public Pawn(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected boolean validDirection(Direction direction) {
		if (this.team == Team.WHITE) {
			return Direction.whitePawnDirection().contains(direction);
		}
		return Direction.blackPawnDirection().contains(direction);
	}

}
