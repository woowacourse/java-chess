package chess.domain.direction;

import java.util.List;

import chess.domain.Position;

public class DirectionGenerator {

	public static Direction generateOfBlackPawn(Position from, Position to) {
		List<Direction> blackPawn = List.of(
			BasicDirection.SOUTH,
			DiagonalDirection.SOUTH_EAST,
			DiagonalDirection.SOUTH_WEST
		);
		return blackPawn.stream()
			.filter(direction -> direction.confirm(from, to))
			.findAny()
			.orElseThrow(IllegalArgumentException::new);
	}

	public static Direction generateOfWhitePawn(Position from, Position to) {
		List<Direction> whitePawn = List.of(
			BasicDirection.NORTH,
			DiagonalDirection.NORTH_EAST,
			DiagonalDirection.NORTH_WEST
		);
		return whitePawn.stream()
			.filter(direction -> direction.confirm(from, to))
			.findAny()
			.orElseThrow(IllegalArgumentException::new);
	}
}
