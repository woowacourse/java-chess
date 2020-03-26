package chess.piece.type;


import chess.board.Location;
import chess.team.Team;

public abstract class Piece {
	protected final char name;

	Piece(char name) {
		this.name = name;
	}

	public abstract boolean canMove(Location now, Location after);

	public boolean isSameTeam(boolean black) {
		return Character.isUpperCase(name) == black;
	}

	@Override
	public String toString() {
		return String.valueOf(name);
	}
}
