package chess.domain.piece;

import java.util.Map;

import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.piece.king.King;
import chess.domain.position.Position;

public abstract class Piece {
	protected final MovingStrategy strategy;
	protected final Team team;
	protected Position position;

	public Piece(MovingStrategy movingStrategy, Team team, Position position) {
		this.strategy = movingStrategy;
		this.team = team;
		this.position = position;
	}

	public abstract String toString();

	public boolean isTurn(Turn turn) {
		return turn.isSameTeam(team);
	}

	public abstract Piece move(Position from, Position to, Map<Position, Team> teamBoard);

	public boolean isKing() {
		return this instanceof King;
	}

	public Position getPosition() {
		return position;
	}

	public Team getTeam() {
		return team;
	}

	public abstract double getScore();
}
