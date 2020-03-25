package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Rook extends Piece {
	public Rook(Position position, Team team) {
		super(position, team);
	}

	@Override
	public String showSymbol() {
		if (this.team == Team.WHITE) {
			return "r";
		}
		return "R";
	}
}
