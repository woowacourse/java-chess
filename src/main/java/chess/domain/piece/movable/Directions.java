package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

import static chess.domain.piece.movable.Direction.*;

public enum Directions {
	LINEAR(Arrays.asList(NORTH, EAST, SOUTH, WEST)),
	DIAGONAL(Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST)),
	EVERY(Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST)),
	KNIGHT(Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS)),
	WHITEPAWN(Arrays.asList(NORTH, NORTHEAST, NORTHWEST)),
	BLACKPAWN(Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST)),
	WHITEPAWNINITIAL(Arrays.asList(NORTH, NORTHEAST, NORTHWEST, NORTHDOUBLE)),
	BLACKPAWNINITIAL(Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST, SOUTHDOUBLE));

	private List<Direction> directions;

	Directions(List<Direction> directions) {
		this.directions = directions;
	}

	public List<Direction> getDirections() {
		return directions;
	}

	public static Directions getPawnDirectionsBy(Color color, Position position) {
		if (color.isWhite() && position.getColumn().getValue() == 2) {
			return WHITEPAWNINITIAL;
		}

		if (color.isBlack() && position.getColumn().getValue() == 7) {
			return BLACKPAWNINITIAL;
		}

		if (color.isWhite()) {
			return WHITEPAWN;
		}

		if (color.isBlack()) {
			return BLACKPAWN;
		}
		throw new IllegalArgumentException("해당 Direction을 찾을 수 없습니다.");
	}
}
