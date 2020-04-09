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

	public boolean isBlack() {
		return team.isSameTeam(Team.BLACK);
	}

	public abstract Piece move(Position from, Position to, Map<Position, Team> teamBoard);

	public abstract double getScore();

	public abstract String getSymbol();

	public boolean isKing() {
		return this instanceof King;
	}

	public boolean isPawn() {
		return false;
	}

	public boolean isTurn(Turn turn) {
		return turn.isSameTeam(team);
	}

	public boolean isNotNone() {
		return true;
	};

	public Position getPosition() {
		return position;
	}

	public int getColumnValue() {
		return position.getColumnIntValue();
	}

	public int getRowValue() {
		return position.getRowIntValue();
	}

	public Team getTeam() {
		return team;
	}
}
