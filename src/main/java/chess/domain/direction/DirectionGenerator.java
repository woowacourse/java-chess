package chess.domain.direction;

import java.util.ArrayList;
import java.util.List;

import chess.domain.Position;

public class DirectionGenerator {

	public static Direction generate(Position from, Position to) {
		return addAllDirections().stream()
			.filter(direction -> direction.confirm(from, to))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	private static List<Direction> addAllDirections() {
		List<Direction> directions = new ArrayList<>();
		directions.addAll(List.of(BasicDirection.values()));
		directions.addAll(List.of(DiagonalDirection.values()));
		directions.addAll(List.of(KnightDirection.values()));
		return directions;
	}
}
