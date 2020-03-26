package chess.piece.type;

import java.util.List;

import chess.board.Location;
import chess.piece.location.strategy.LocationStrategy;

public abstract class Piece {
	protected final char name;
	protected final LocationStrategy locationStrategy;

	Piece(char name, LocationStrategy locationStrategy) {
		this.name = name;
		this.locationStrategy = locationStrategy;
	}

	public abstract boolean canMove(Location now, Location after);

	public List<Location> getInitialLocation() {
		return locationStrategy.getBlackTeamLocations();
	}

	public List<Location> reverseInitialLocation() {
		return locationStrategy.getWhiteTeamLocations();
	}

	public boolean isSameTeam(boolean black) {
		return Character.isUpperCase(name) == black;
	}

	@Override
	public String toString() {
		return String.valueOf(name);
	}
}
