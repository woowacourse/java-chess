package chess.piece.location.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.board.Location;

public class BishopLocationStrategy extends NoblePieceStrategy {

	private static final Location LEFT_BISHOP = new Location(1, 'c');
	private static final Location RIGHT_BISHOP = new Location(1, 'f');

	@Override
	public List<Location> getInitialLocation() {
		final List<Location> lookLocations = new ArrayList<>();
		lookLocations.add(LEFT_BISHOP);
		lookLocations.add(RIGHT_BISHOP);
		return Collections.unmodifiableList(lookLocations);
	}
}
