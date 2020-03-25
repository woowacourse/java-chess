package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Queen extends Piece {
	public Queen(Position position, Team team) {
		super(position, team);
	}

	@Override
	public String showSymbol() {
		if (this.team == Team.WHITE) {
			return "q";
		}
		return "Q";
	}
}
