package chess.piece.location.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.board.Location;

public class KingLocationStrategy extends NoblePieceStrategy {

	private static final Location KING_LOCATION = new Location(1, 'e');

	@Override
	public List<Location> getInitialLocation() {
		final List<Location> kingLocations = new ArrayList<>();
		kingLocations.add(KING_LOCATION);
		return Collections.unmodifiableList(kingLocations);
	}
}
