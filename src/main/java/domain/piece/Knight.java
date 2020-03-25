package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Knight extends Piece {
	private static final String SYMBOL = "n";

	public Knight(Position position, Team team) {
		super(position, team);
	}
}
