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
			.filter(position -> isDiagonal(position, pivot))
			.collect(Collectors.toList());
	}

	private static boolean isDiagonal(Position position, Position pivot) {
		return Math.abs(position.getColumnGap(pivot)) == Math.abs(position.getRowGap(pivot));
	}

	public static List<Position> kingOf(Position pivot) {
		return Position.getPositions()
			.stream()
			.filter(position -> !position.equals(pivot))
			.filter(position -> isAround(pivot, position))
			.collect(Collectors.toList());
	}

	private static boolean isAround(Position pivot, Position position) {
		return Math.abs(position.getColumnGap(pivot)) <= 1 && Math.abs(position.getRowGap(pivot)) <= 1;
	}

	public static List<Position> knightOf(Position pivot) {
		return Position.getPositions()
			.stream()
			.filter(position -> isKnight(pivot, position))
			.collect(Collectors.toList());
	}

	private static boolean isKnight(Position pivot, Position position) {
		return (Math.abs(position.getColumnGap(pivot)) == 2 && Math.abs(position.getRowGap(pivot)) == 1) ||
			(Math.abs(position.getColumnGap(pivot)) == 1 && Math.abs(position.getRowGap(pivot)) == 2);
	}

	public static List<Position> pawnOf(Position pivot) {
		return Position.getPositions()
			.stream()
			.filter(position -> isPawn(pivot, position))
			.collect(Collectors.toList());
	}

	private static boolean isPawn(Position pivot, Position position) {
		return position.getRowGap(pivot) == 1 && Math.abs(position.getColumnGap(pivot)) <= 1;
	}
}
