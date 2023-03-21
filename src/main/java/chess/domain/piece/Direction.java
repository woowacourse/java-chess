package chess.domain.piece;

import java.util.Collections;
import java.util.List;

import chess.domain.RelativePosition;

public enum Direction {

	NORTH(new RelativePosition(0, 1)),
	NORTH_NORTH_EAST(new RelativePosition(1, 2)),
	NORTH_EAST(new RelativePosition(1, 1)),
	NORTH_EAST_EAST(new RelativePosition(2, 1)),
	EAST(new RelativePosition(1, 0)),
	SOUTH_EAST_EAST(new RelativePosition(2, -1)),
	SOUTH_EAST(new RelativePosition(1, -1)),
	SOUTH_SOUTH_EAST(new RelativePosition(1, -2)),
	SOUTH(new RelativePosition(0, -1)),
	SOUTH_SOUTH_WEST(new RelativePosition(-1, -2)),
	SOUTH_WEST(new RelativePosition(-1, -1)),
	SOUTH_WEST_WEST(new RelativePosition(-2, -1)),
	WEST(new RelativePosition(-1, 0)),
	NORTH_WEST_WEST(new RelativePosition(-2, 1)),
	NORTH_WEST(new RelativePosition(-1, 1)),
	NORTH_NORTH_WEST(new RelativePosition(-1, 2));

	private final RelativePosition unitRelativePosition;

	Direction(final RelativePosition unitRelativePosition) {
		this.unitRelativePosition = unitRelativePosition;
	}

	public static List<Direction> getCrossAndDiagonal() {
		return List.of(NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST);
	}

	public static List<Direction> getCross() {
		return List.of(NORTH, EAST, SOUTH, WEST);
	}

	public static List<Direction> getDiagonal() {
		return List.of(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
	}

	public static List<Direction> getLShaped() {
		return List.of(NORTH_NORTH_EAST, NORTH_EAST_EAST, SOUTH_EAST_EAST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
			SOUTH_WEST_WEST, NORTH_WEST_WEST, NORTH_NORTH_WEST);
	}

	public static List<Direction> getNorth() {
		return List.of(NORTH);
	}

	public static List<Direction> getNorthern() {
		return List.of(NORTH, NORTH_EAST, NORTH_WEST);
	}

	public static List<Direction> getEmpty() {
		return Collections.EMPTY_LIST;
	}

	public boolean matches(RelativePosition relativePosition) {
		return unitRelativePosition.equals(relativePosition);
	}
}
