package chess.piece;

import chess.position.Position;

public abstract class Piece {
	private final Team team;
	private final Position position;

	public Piece(Team team, Position position) {
		this.team = team;
		this.position = position;
	}
}
