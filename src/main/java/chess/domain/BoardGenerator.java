package chess.domain;

import chess.domain.moverule.*;

import java.util.*;

public class BoardGenerator {
	private static final List<MoveRule> MOVE_RULES;
	private static final int PAWN_OFFSET = 1;
	private static final int ROW_OF_TOP_PAWN = Row.MAX - PAWN_OFFSET;
	private static final int ROW_OF_BOTTOM_PAWN = Row.MIN + PAWN_OFFSET;
	private static final int EMPTY_OFFSET = 2;
	private static final int ROW_OF_EMPTY_START = Row.MAX - EMPTY_OFFSET;
	private static final int ROW_OF_EMPTY_END = Row.MIN + EMPTY_OFFSET;

	static {
		MOVE_RULES = Arrays.asList(Rook.getInstance(), Knight.getInstance()
				, Bishop.getInstance(), Queen.getInstance()
				, King.getInstance(), Bishop.getInstance()
				, Knight.getInstance(), Rook.getInstance());
	}

	private BoardGenerator() {
	}

	public static Map<Position, Square> generate() {
		Map<Position, Square> map = new TreeMap<>();
		putPiecesOfColor(map, Piece.Color.BLACK, Row.MAX);
		putTopPawnsOfColor(map, Piece.Color.BLACK, ROW_OF_TOP_PAWN);
		putEmpty(map, ROW_OF_EMPTY_START, ROW_OF_EMPTY_END);
		putBottomPawnsOfColor(map, Piece.Color.WHITE, ROW_OF_BOTTOM_PAWN);
		putPiecesOfColor(map, Piece.Color.WHITE, Row.MIN);

		return map;
	}

	private static void putPiecesOfColor(final Map<Position, Square> map, final Piece.Color color, final int rowIndex) {
		List<Position> positions = getColumnsOfRow(rowIndex);
		final int columnSize = positions.size();
		for (int i = 0; i < columnSize; i++) {
			map.put(positions.get(i), Square.of(positions.get(i), Piece.of(color, MOVE_RULES.get(i))));
		}
	}

	private static void putTopPawnsOfColor(final Map<Position, Square> map, final Piece.Color color, final int rowIndex) {
		List<Position> positions = getColumnsOfRow(rowIndex);
		Piece piece = Piece.of(color, Pawn.FIRST_TOP);
		putPawns(map, positions, piece);
	}

	private static void putBottomPawnsOfColor(final Map<Position, Square> map
			, final Piece.Color color, final int rowIndex) {
		List<Position> positions = getColumnsOfRow(rowIndex);
		Piece piece = Piece.of(color, Pawn.FIRST_BOTTOM);
		putPawns(map, positions, piece);
	}

	private static void putEmpty(final Map<Position, Square> map, final int from, final int to) {
		List<Position> positions;
		Piece piece = Piece.of(Piece.Color.EMPTY, Empty.getInstance());
		for (int i = from; i >= to; i--) {
			positions = getColumnsOfRow(i);
			putPawns(map, positions, piece);
		}
	}

	private static List<Position> getColumnsOfRow(final int rowIndex) {
		List<Position> positions = new ArrayList<>();
		for (int i = Column.MIN; i <= Column.MAX; i++) {
			Position position = Position.of(String.valueOf(rowIndex), String.valueOf((char) i));
			positions.add(position);
		}
		return positions;
	}

	private static void putPawns(Map<Position, Square> map, List<Position> positions, Piece piece) {
		for (Position position : positions) {
			map.put(position, Square.of(position, piece));
		}
	}
}
