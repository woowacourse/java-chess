package chess.piece.type;

import java.util.Map;

import chess.board.Location;

public abstract class Piece {
	protected final char name;

	Piece(char name) {
		this.name = name;
	}

	public abstract boolean canMove(Location now, Location after);

	public boolean isSameTeam(boolean black) {
		return isBlack() == black;
	}

	protected boolean isBlack() {
		return Character.isUpperCase(name);
	}

	@Override
	public String toString() {
		return String.valueOf(name);
	}

	public boolean hasObstacle(Map<Location, Piece> board, Location now, Location destination) {
		Location nowLocation = new Location(now);
		System.out.println(nowLocation);
		for (int weight = 1; nowLocation != destination; weight++) {
			nowLocation = now.calculateNextLocation(destination, weight);
			System.out.println(nowLocation);
			if (board.containsKey(nowLocation)) {
				return true;
			}
		}
		return false;
	}
}
