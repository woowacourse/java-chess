package chess.piece.location.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.board.Location;

public class RookLocationsStrategy extends NoblePieceStrategy {

	private static final Location ROOK_LEFT = new Location(1, 'a');
	private static final Location ROOK_RIGHT = new Location(1, 'h');

	@Override
	public List<Location> getInitialLocation() {
		final List<Location> lookLocations = new ArrayList<>();
		lookLocations.add(ROOK_LEFT);
		lookLocations.add(ROOK_RIGHT);
		return Collections.unmodifiableList(lookLocations);
	}
}
