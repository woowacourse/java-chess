package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Pawn extends Piece{
	public Pawn(Position position, Team team) {
		super(position, team);
	}

	@Override
	public String showSymbol() {
		if (this.team == Team.WHITE) {
			return "p";
		}
		return "P";
	}
}
