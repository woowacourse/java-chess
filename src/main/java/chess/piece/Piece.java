package chess.piece;

import java.util.Objects;

import chess.board.Location;
import chess.piece.stategy.MoveStrategy;
import chess.team.Team;

public abstract class Piece {
	protected final Team team;
	protected final char name;
	protected final MoveStrategy moveStrategy;

	public Piece(Team team, char name, MoveStrategy moveStrategy) {
		this.team = team;
		this.name = name;
		this.moveStrategy = moveStrategy;
	}

	protected static char editName(char c, Team team) {
		if (Team.BLACK == team) {
			return Character.toUpperCase(c);
		}
		return c;
	}

	public abstract double getScore();

	public abstract boolean isNotJumper();

	public boolean isSameTeam(Team team) {
		return team == this.team;
	}

	public boolean isSameTeam(Piece piece) {
		Objects.requireNonNull(piece, "piece가 존재하지않습니다.");
		return piece.team == this.team;
	}

	public void checkStrategy(Location now, Location destination, boolean destinationLocationEnemy) {
		moveStrategy.checkMove(now, destination, destinationLocationEnemy);
	}

	public boolean isNotSameTeam(Piece piece) {
		return !isSameTeam(piece);
	}

	public boolean isPawn() {
		return this instanceof Pawn;
	}

	public boolean isKing() {
		return this instanceof King;
	}

	public char getName() {
		return name;
	}
}
