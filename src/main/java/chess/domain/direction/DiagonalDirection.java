package chess.domain.direction;

import java.util.function.BiPredicate;

import chess.domain.Position;

public enum DiagonalDirection implements Direction {
	
	NORTH_EAST(
		(rowDifference, columnDifference) -> rowDifference < 0 && columnDifference < 0,
		(rowDifference, columnDifference) -> rowDifference - columnDifference == 0
	),
	NORTH_WEST(
		(rowDifference, columnDifference) -> rowDifference < 0 && columnDifference > 0,
		(rowDifference, columnDifference) -> rowDifference + columnDifference == 0
	),
	SOUTH_EAST(
		(rowDifference, columnDifference) -> rowDifference > 0 && columnDifference < 0,
		(rowDifference, columnDifference) -> rowDifference + columnDifference == 0
	),
	SOUTH_WEST(
		(rowDifference, columnDifference) -> rowDifference > 0 && columnDifference > 0,
		(rowDifference, columnDifference) -> rowDifference - columnDifference == 0);

	private final BiPredicate<Integer, Integer> quadrantPredicate;
	private final BiPredicate<Integer, Integer> slopePredicate;
	
	DiagonalDirection(
		BiPredicate<Integer, Integer> quadrantPredicate,
		BiPredicate<Integer, Integer> slopePredicate) {
		this.quadrantPredicate = quadrantPredicate;
		this.slopePredicate = slopePredicate;
	}
	
	@Override
	public boolean confirm(Position from, Position to) {
		int rowDifference = from.subtractRow(to);
		int columnDifference = from.subtractColumn(to);
		return this.quadrantPredicate.test(rowDifference, columnDifference) &&
			this.slopePredicate.test(rowDifference, columnDifference);
	}
}
