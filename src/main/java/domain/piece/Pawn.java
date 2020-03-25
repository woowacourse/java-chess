package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Pawn extends Piece{
	private static final String SYMBOL = "p";

	public Pawn(Position position, Team team) {
		super(position, team);
	}
}
