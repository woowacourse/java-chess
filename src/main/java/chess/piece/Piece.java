package chess.piece;

import chess.position.Position;

public abstract class Piece {
	private final Team team;

	public Piece(Team team) {
		this.team = team;
	}
}
