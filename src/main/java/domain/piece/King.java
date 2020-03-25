package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class King extends Piece{
	public King(Position position, Team team) {
		super(position, team);
	}

	@Override
	public String showSymbol() {
		if (this.team == Team.WHITE) {
			return "k";
		}
		return "K";
	}
}
