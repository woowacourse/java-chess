package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Rook extends Piece {
	private static final String SYMBOL = "r";

	public Rook(Position position, Team team) {
		super(position, team);
	}
}
