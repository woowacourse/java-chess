package chess.piece;

import java.util.Map;
import java.util.Objects;

import chess.board.Location;
import chess.team.Team;

public abstract class Piece {
	protected final Team team;

	public Piece(Team team) {
		this.team = team;
	}

	public abstract boolean checkRange(Location now, Location after);

	public abstract double getScore();

	protected abstract char getName();

	public boolean isSameTeam(Team team) {
		return team == this.team;
	}

	public boolean isSameTeam(Piece piece) {
		return piece.team == this.team;
	}

	public boolean checkObstacle(Map<Location, Piece> board, Location now, Location destination) {
		for (int weight = 1; ; weight++) {
			Location nowLocation = now.calculateNextLocation(destination, weight);
			if (nowLocation.equals(destination)) {
				break;
			}
			if (board.containsKey(nowLocation)) {
				return true;
			}
		}
		return false;
	}

	public boolean isPawn() {
		return this instanceof Pawn;
	}

	@Override
	public String toString() {
		if (team == Team.BLACK) {
			return String.valueOf(Character.toUpperCase(getName()));
		}
		return String.valueOf(getName());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return team == piece.team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(team);
	}
}
