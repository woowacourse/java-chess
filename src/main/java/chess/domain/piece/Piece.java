package chess.domain.piece;

import chess.domain.Position;

public abstract class Piece {
	protected final Team team;

	public Piece(Position position, Team team) {
		this.team = team;
	}

	public abstract void move();

	@Override
	public String toString() {
		return "*";
	}
}
