package chess.domain;

import chess.domain.RuleImpl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BoardGenerator {
	private static final char START_COLUMN = 'a';
	private static final char LAST_COLUMN = 'h';

	private BoardGenerator() {
	}

	public static Map<Position, Square> generate() {
		Map<Position, Square> map = new TreeMap<>();
		putBlackPieces(map);
		putBlackPawns(map);

		putEmptys(map);

		putWhitePawns(map);
		putWhitePieces(map);

		return map;
	}

	private static void putWhitePieces(Map<Position, Square> map) {
		List<Position> positions;

		positions = getColumnsOfRow(1);
		map.put(positions.get(0), Square.of(positions.get(0), Piece.of(Piece.Color.WHITE, Rook.getInstance())));
		map.put(positions.get(1), Square.of(positions.get(1), Piece.of(Piece.Color.WHITE, Knight.getInstance())));
		map.put(positions.get(2), Square.of(positions.get(2), Piece.of(Piece.Color.WHITE, Bishop.getInstance())));
		map.put(positions.get(3), Square.of(positions.get(3), Piece.of(Piece.Color.WHITE, Queen.getInstance())));
		map.put(positions.get(4), Square.of(positions.get(4), Piece.of(Piece.Color.WHITE, King.getInstance())));
		map.put(positions.get(5), Square.of(positions.get(5), Piece.of(Piece.Color.WHITE, Bishop.getInstance())));
		map.put(positions.get(6), Square.of(positions.get(6), Piece.of(Piece.Color.WHITE, Knight.getInstance())));
		map.put(positions.get(7), Square.of(positions.get(7), Piece.of(Piece.Color.WHITE, Rook.getInstance())));
	}

	private static void putBlackPieces(Map<Position, Square> map) {
		List<Position> positions = getColumnsOfRow(8);
		map.put(positions.get(0), Square.of(positions.get(0), Piece.of(Piece.Color.BLACK, Rook.getInstance())));
		map.put(positions.get(1), Square.of(positions.get(1), Piece.of(Piece.Color.BLACK, Knight.getInstance())));
		map.put(positions.get(2), Square.of(positions.get(2), Piece.of(Piece.Color.BLACK, Bishop.getInstance())));
		map.put(positions.get(3), Square.of(positions.get(3), Piece.of(Piece.Color.BLACK, Queen.getInstance())));
		map.put(positions.get(4), Square.of(positions.get(4), Piece.of(Piece.Color.BLACK, King.getInstance())));
		map.put(positions.get(5), Square.of(positions.get(5), Piece.of(Piece.Color.BLACK, Bishop.getInstance())));
		map.put(positions.get(6), Square.of(positions.get(6), Piece.of(Piece.Color.BLACK, Knight.getInstance())));
		map.put(positions.get(7), Square.of(positions.get(7), Piece.of(Piece.Color.BLACK, Rook.getInstance())));
	}

	private static void putBlackPawns(Map<Position, Square> map) {
		List<Position> positions = getColumnsOfRow(7);
		for (Position position : positions) {
			map.put(position, Square.of(position, Piece.of(Piece.Color.BLACK, Pawn.FIRST_TOP)));
		}
	}

	private static void putEmptys(Map<Position, Square> map) {
		List<Position> positions;
		for (int i = 6; i >= 3; i--) {
			positions = getColumnsOfRow(i);
			for (Position position : positions) {
				map.put(position, Square.of(position, Piece.of(Piece.Color.EMPTY, Empty.getInstance())));
			}
		}
	}

	private static void putWhitePawns(Map<Position, Square> map) {
		List<Position> positions = getColumnsOfRow(2);
		for (Position position : positions) {
			map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));
		}
	}

	private static List<Position> getColumnsOfRow(final int rowIndex) {
		List<Position> positions = new ArrayList<>();
		for (int i = START_COLUMN; i <= LAST_COLUMN; i++) {
			Position position = Position.of(String.valueOf(rowIndex), String.valueOf((char) i));
			positions.add(position);
		}
		return positions;
	}
}
