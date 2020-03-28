package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

public abstract class Piece {
	protected static MovingStrategy movingStrategy;

	protected final Team team;
	protected final Position position;

	public Piece(Team team, Position position) {
		this.team = team;
		this.position = position;
		movingStrategy = setMovingStrategy(team);
	}

	public abstract MovingStrategy setMovingStrategy(Team team);

	public abstract String toString();
}
