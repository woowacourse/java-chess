package chess.piece.location.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.board.Location;

public class PawnLocationStrategy implements LocationStrategy {
	private static final int PAWN_COUNT = 8;
	private static final int PAWN_INITIAL_ROW = 2;
	private static final char PAWN_INITIAL_COL = 'a';
	private static final int REVERSE_DIFFERENCE_COUNT = 5;

	@Override
	public List<Location> getInitialLocation() {
		final List<Location> pawnLocations = new ArrayList<>();
		for (int i = 0; i < PAWN_COUNT; i++) {
			pawnLocations.add(new Location(PAWN_INITIAL_ROW, (char)(PAWN_INITIAL_COL + i)));
		}
		return Collections.unmodifiableList(pawnLocations);
	}

	@Override
	public List<Location> reverseInitialLocation() {
		List<Location> initialLocations = new ArrayList<>(getInitialLocation());
		for (Location location : initialLocations) {
			initialLocations.add(location.moveRowBy(REVERSE_DIFFERENCE_COUNT));
		}
		return Collections.unmodifiableList(initialLocations);
	}
}
