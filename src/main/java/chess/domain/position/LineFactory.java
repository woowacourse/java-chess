package chess.domain.position;

import java.util.List;
import java.util.stream.Collectors;

public class LineFactory {
	public static List<Position> columnOf(Position pivot) {
		return Position.getPositions()
			.stream()
			.filter(position -> position.isRowEquals(pivot))
			.collect(Collectors.toList());
	}

	public static List<Position> rowOf(Position pivot) {
		return Position.getPositions()
			.stream()
			.filter(position -> position.isColumnEquals(pivot))
			.collect(Collectors.toList());
	}
}
