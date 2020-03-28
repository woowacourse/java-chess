package chess.piece;

import java.util.Map;
import java.util.Objects;

import chess.board.Location;
import chess.team.Team;

public abstract class Piece {
	protected final char name;

	Piece(char name) {
		this.name = name;
	}

	public abstract boolean canMove(Location now, Location after);

	public abstract double getScore(boolean hasVerticalEnemy);

	protected boolean isBlack() {
		return Character.isUpperCase(name);
	}

	public boolean isSameTeam(boolean black) {
		return isBlack() == black;
	}

	public boolean isSameTeam(Team blackTeam) {
		return blackTeam == Team.of(isBlack());
	}

	public boolean isSameTeam(Piece piece) {
		return isBlack() == piece.isBlack();
	}

	@Override
	public String toString() {
		return String.valueOf(name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return name == piece.name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	public boolean hasObstacle(Map<Location, Piece> board, Location now, Location destination) {
		Location nowLocation = new Location(now);

		for (int weight = 1; ; weight++) {
			nowLocation = now.calculateNextLocation(destination, weight);
			if (nowLocation.equals(destination)) {
				break;
			}
			System.out.println(nowLocation);
			if (board.containsKey(nowLocation)) {
				return true;
			}
		}
		return false;
	}
}
