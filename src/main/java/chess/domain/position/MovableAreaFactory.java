package chess.domain.position;

import java.util.List;
import java.util.stream.Collectors;

public class MovableAreaFactory {
	public static List<Position> columnOf(Position pivot) {
		return Position.getPositions()
			.stream()
			.filter(position -> !position.equals(pivot))
			.filter(position -> position.isRowEquals(pivot))
			.collect(Collectors.toList());
	}

	public static List<Position> rowOf(Position pivot) {
		return Position.getPositions()
			.stream()
			.filter(position -> !position.equals(pivot))
			.filter(position -> position.isColumnEquals(pivot))
			.collect(Collectors.toList());
	}

	public static List<Position> diagonalsOf(Position pivot) {
		return Position.getPositions()
			.stream()
			.filter(position -> !position.equals(pivot))
			.filter(position -> Math.abs(position.getColumnGap(pivot)) == Math.abs(position.getRowGap(pivot)))
			.collect(Collectors.toList());
	}

	public static List<Position> kingOf(Position pivot) {
		return Position.getPositions()
			.stream()
			.filter(position -> !position.equals(pivot))
			.filter(position -> Math.abs(position.getColumnGap(pivot)) <= 1 && Math.abs(position.getRowGap(pivot)) <= 1)
			.collect(Collectors.toList());
	}
}
