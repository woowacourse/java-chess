package chess.domain.piece;

import java.util.Map;

import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;

public abstract class Piece {
	protected final Team team;
	protected Position position;

	public Piece(Team team, Position position) {
		this.team = team;
		this.position = position;
	}

	public abstract String toString();

	public boolean isTurn(Turn turn) {
		return turn.isSameTeam(team);
	}

	public abstract Piece move(Position from, Position to, Map<Position, Team> dto);

	public Team getTeam() {
		return team;
	}

	public Position getPosition() {
		return position;
	}
}
