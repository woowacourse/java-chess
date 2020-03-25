package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Knight extends Piece {
	public Knight(Position position, Team team) {
		super(position, team);
	}

	@Override
	public String showSymbol() {
		if (this.team == Team.WHITE) {
			return "n";
		}
		return "N";
	}
}
