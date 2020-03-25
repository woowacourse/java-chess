package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Queen extends Piece {
	private static final String SYMBOL = "q";

	public Queen(Position position, Team team) {
		super(position, team);
	}
}
