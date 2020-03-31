package chess.piece;

import java.util.Objects;

import chess.board.Location;
import chess.piece.stategy.MoveStrategy;
import chess.team.Team;

public abstract class Piece {
	protected final Team team;
	protected final MoveStrategy moveStrategy;

	public Piece(Team team, MoveStrategy moveStrategy) {
		this.team = team;
		this.moveStrategy = moveStrategy;
	}

	public abstract double getScore();

	public abstract boolean isNotJumper();

	protected abstract char getName();

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

	@Override
	public String toString() {
		if (team == Team.BLACK) {
			return String.valueOf(Character.toUpperCase(getName()));
		}
		return String.valueOf(getName());
	}
}
