package chess.piece.type;

import chess.board.Location;

public abstract class Piece {
	protected final char name;

	Piece(char name) {
		this.name = name;
	}

	public abstract boolean canMove(Location now, Location after);

	public boolean isSameTeam(boolean black) {
		return isBlack() == black;
	}

	protected boolean isBlack() {
		return Character.isUpperCase(name);
	}

	@Override
	public String toString() {
		return String.valueOf(name);
	}
}
