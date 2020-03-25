package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Bishop extends Piece {
	public Bishop(Position position, Team team) {
		super(position, team);
	}

	@Override
	public String showSymbol() {
		if (this.team == Team.WHITE) {
			return "b";
		}
		return "B";
	}
}
