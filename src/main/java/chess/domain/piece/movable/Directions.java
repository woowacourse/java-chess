package chess.domain.piece.movable;

import chess.domain.piece.Color;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chess.domain.piece.movable.Direction.*;

public enum Directions {
	LINEAR(Arrays.asList(NORTH, EAST, SOUTH, WEST)),
	DIAGONAL(Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST)),
	EVERY(Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST)),
	KNIGHT(Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS)),
	WHITEPAWN(Arrays.asList(NORTH, NORTHEAST, NORTHWEST)),
	BLACKPAWN(Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST)),
	NONE(Collections.emptyList());

	private List<Direction> directions;

	Directions(List<Direction> directions) {
		this.directions = directions;
	}

	public List<Direction> getDirections() {
		return directions;
	}

	public static Directions getPawnDirectionsBy(Color color) {
		if (color.isWhite()) {
			return WHITEPAWN;
		}

		if (color.isBlack()) {
			return BLACKPAWN;
		}
		throw new IllegalArgumentException("해당 Direction을 찾을 수 없습니다.");
	}
}
