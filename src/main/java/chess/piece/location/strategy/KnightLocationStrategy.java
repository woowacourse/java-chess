package chess.piece.location.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.board.Location;

public class KnightLocationStrategy extends NoblePieceStrategy {

	private static final Location LEFT_KNIGHT = new Location(1, 'b');
	private static final Location RIGHT_KNIGHT = new Location(1, 'g');

	@Override
	public List<Location> getWhiteTeamLocations() {
		final List<Location> knightLocations = new ArrayList<>();
		knightLocations.add(LEFT_KNIGHT);
		knightLocations.add(RIGHT_KNIGHT);
		return Collections.unmodifiableList(knightLocations);
	}
}
