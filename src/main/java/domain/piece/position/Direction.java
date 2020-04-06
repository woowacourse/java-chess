package domain.piece.position;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

import domain.board.Board;
import domain.piece.Piece;

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

	public static final int NO_CELL_TO_CHECK = 0;

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
		int countOfCellToCheck = calculateLoopCount(position, targetPosition) - 1;

		if (countOfCellToCheck == NO_CELL_TO_CHECK) {
			return false;
		}

		return checkPieceInRoute(board, countOfCellToCheck, position);
	}

	private boolean checkPieceInRoute(Board board, int countOfCellToCheck, Position position) {
		int routeRow = position.getRowNumber();
		int routeColumn = position.getColumnNumber();
		int loopCount = 0;
		Optional<Piece> piece;

		do {
			routeRow += this.rowGap;
			routeColumn += this.columnGap;
			piece = board.findPiece(Position.of(routeColumn, routeRow));
			loopCount++;
		}
		while (loopCount < countOfCellToCheck && !piece.isPresent());

		return piece.isPresent();
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