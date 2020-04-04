package chess.domain.position;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.Arrays;
import java.util.List;

public enum Column {
	EIGHTH("8", 8),
	SEVENTH("7", 7),
	SIXTH("6", 6),
	FIFTH("5", 5),
	FOURTH("4", 4),
	THIRD("3", 3),
	SECOND("2", 2),
	FIRST("1", 1);

	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "옳지 않은 좌표 입력입니다.";
	private static final int BLACK_PAWN_INITIAL_COLUMN = 7;
	private static final int WHITE_PAWN_INITIAL_COLUMN = 2;
	private static final double BOTTOM_ZONE_END_POINT = 4;

	private final String name;
	private final int value;

	Column(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static Column of(String name) {
		return Arrays.stream(Column.values())
				.filter(column -> column.name.equals(name))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE));
	}

	public Column calculate(int value) {
		return Arrays.stream(Column.values())
				.filter(column -> column.value == this.value + value)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException((INVALID_INPUT_EXCEPTION_MESSAGE)));
	}

	public boolean isPawnInitial() {
		return isWhitePawnInitial() || isBlackPawnInitial();
	}

	public boolean isWhitePawnInitial() {
		return value == WHITE_PAWN_INITIAL_COLUMN;
	}

	public boolean isBlackPawnInitial() {
		return value == BLACK_PAWN_INITIAL_COLUMN;
	}

	public Color getColor() {
		if (isOnHalfBottom()) {
			return Color.WHITE;
		}
		return Color.BLACK;
	}

	public boolean isOnHalfBottom() {
		return value <= BOTTOM_ZONE_END_POINT;
	}

	public static List<Column> getInitialColumns() {
		return Arrays.asList(EIGHTH, SEVENTH, SECOND, FIRST);
	}

	public PieceType getPawnType() {
		if (isWhitePawnInitial()) {
			return PieceType.WHITE_PAWN;
		}
		return PieceType.BLACK_PAWN;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
}
