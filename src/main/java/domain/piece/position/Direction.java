package domain.piece.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import domain.board.Board;

public enum Direction {
	N(1, 0, (rowGap, columnGap) -> rowGap > 0 && columnGap == 0),
	S(-1, 0, (rowGap, columnGap) -> rowGap < 0 && columnGap == 0),
	E(0, 1, (rowGap, columnGap) -> rowGap == 0 && columnGap > 0),
	W(0, -1, (rowGap, columnGap) -> rowGap == 0 && columnGap < 0),

	NE(1, 1, (rowGap, columnGap) -> isSameAbs(rowGap, columnGap) && rowGap > 0 && columnGap > 0),
	NW(1, -1, (rowGap, columnGap) -> isSameAbs(rowGap, columnGap) && rowGap > 0 && columnGap < 0),
	SE(-1, 1, (rowGap, columnGap) -> isSameAbs(rowGap, columnGap) && rowGap < 0 && columnGap > 0),
	SW(-1, -1, (rowGap, columnGap) -> isSameAbs(rowGap, columnGap) && rowGap < 0 && columnGap < 0),

	NNE(2, 1, (rowGap, columnGap) -> rowGap == 2 && columnGap == 1),
	NNW(2, -1, (rowGap, columnGap) -> rowGap == 2 && columnGap == -1),
	SSE(-2, 1, (rowGap, columnGap) -> rowGap == -2 && columnGap == 1),
	SSW(-2, -1, (rowGap, columnGap) -> rowGap == -2 && columnGap == -1),
	NEE(1, 2, (rowGap, columnGap) -> rowGap == 1 && columnGap == 2),
	NWW(1, -2, (rowGap, columnGap) -> rowGap == 1 && columnGap == -2),
	SEE(-1, 2, (rowGap, columnGap) -> rowGap == -1 && columnGap == 2),
	SWW(-1, -2, (rowGap, columnGap) -> rowGap == -1 && columnGap == -2);

	private int rowGap;
	private int columnGap;
	private BiFunction<Integer, Integer, Boolean> find;

	Direction(int rowGap, int columnGap, BiFunction<Integer, Integer, Boolean> find) {
		this.rowGap = rowGap;
		this.columnGap = columnGap;
		this.find = find;
	}

	private static boolean isSameAbs(int rowGap, int columnGap) {
		return Math.abs(rowGap) == Math.abs(columnGap);
	}

	public static Direction findDirection(Position position, Position targetPosition) {
		int rowGap = position.calculateRowGap(targetPosition);
		int columnGap = position.calculateColumnGap(targetPosition);

		return Arrays.stream(values())
			.filter(direction -> direction.find.apply(rowGap, columnGap))
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION));

	}

	public boolean hasPieceInRoute(Position position, Position targetPosition, Board board) {
		List<Position> route = findRoutes(position, targetPosition);
		return route.stream()
			.map(board::findPiece)
			.anyMatch(Optional::isPresent);
	}

	private List<Position> findRoutes(Position position, Position targetPosition) {
		int loopCount = calculateLoopCount(position, targetPosition) - 1;
		int routeRow = position.getRowNumber();
		int routeColumn = position.getColumnNumber();
		List<Position> route = new ArrayList<>();
		for (int i = 0; i < loopCount; i++) {
			routeRow += this.rowGap;
			routeColumn += this.columnGap;
			route.add(Position.of(routeColumn, routeRow));
		}
		return route;
	}

	private int calculateLoopCount(Position position, Position targetPosition) {
		int columnGap = Math.abs(position.calculateColumnGap(targetPosition));
		int rowGap = Math.abs(position.calculateRowGap(targetPosition));
		return Math.max(columnGap, rowGap);
	}

	public static boolean isEveryDirection(Direction direction) {
		return Arrays.asList(N, S, E, W, NE, NW, SE, SW).contains(direction);
	}

	public static boolean isLinearDirection(Direction direction) {
		return Arrays.asList(N, S, E, W).contains(direction);
	}

	public static boolean isDiagonalDirection(Direction direction) {
		return Arrays.asList(NE, NW, SE, SW).contains(direction);
	}

	public static boolean isKnightDirection(Direction direction) {
		return Arrays.asList(NNE, NNW, SSE, SSW, NEE, NWW, SEE, SWW).contains(direction);
	}

	public static boolean isWhitePawnDirection(Direction direction) {
		return Arrays.asList(N, NE, NW).contains(direction);
	}

	public static boolean isBlackPawnDirection(Direction direction) {
		return Arrays.asList(S, SE, SW).contains(direction);
	}
}