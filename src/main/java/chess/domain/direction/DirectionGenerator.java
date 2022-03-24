package chess.domain.direction;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import chess.domain.Position;

public class DirectionGenerator {

	public static Optional<? extends Direction> generateOfBlackPawn(Position from, Position to) {
		return Stream.of(BasicDirection.SOUTH, DiagonalDirection.SOUTH_EAST, DiagonalDirection.SOUTH_WEST)
			.filter(direction -> direction.confirm(from, to))
			.findAny();
	}

	public static Optional<? extends Direction> generateOfWhitePawn(Position from, Position to) {
		return Stream.of(BasicDirection.NORTH, DiagonalDirection.NORTH_EAST, DiagonalDirection.NORTH_WEST)
			.filter(direction -> direction.confirm(from, to))
			.findAny();
	}

	public static Optional<? extends Direction> generateOfBishop(Position from, Position to) {
		return Arrays.stream(DiagonalDirection.values())
			.filter(direction -> direction.confirm(from, to))
			.findAny();
	}
}
