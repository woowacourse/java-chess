package chess.piece.location.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.board.Location;

public class QueenLocationsStrategy extends NoblePieceStrategy {

	private static final Location QUEEN_LOCATION = new Location(1, 'd');

	@Override
	public List<Location> getWhiteTeamLocations() {
		final List<Location> queenLocations = new ArrayList<>();
		queenLocations.add(QUEEN_LOCATION);
		return Collections.unmodifiableList(queenLocations);
	}
}
