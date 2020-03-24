package chess.piece.location.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.board.Location;

public abstract class NoblePieceStrategy implements LocationStrategy {

	private static final int ROW_VALUE = 7;

	public List<Location> reverseInitialLocation() {
		List<Location> locations = new ArrayList<>(getInitialLocation());

		for (Location location : locations) {
			location.moveRowBy(ROW_VALUE);
		}

		return Collections.unmodifiableList(locations);
	}
}
