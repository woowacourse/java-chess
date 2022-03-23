package chess.domain.board;

import static java.util.Map.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class Board {

	private final Map<Position, Piece> pieces;

	private Board(Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public static Board createInitialBoard() {
		return new Board(createPieces());
	}

	private static Map<Position, Piece> createPieces() {
		Map<Position, Piece> pieces = new HashMap<>();
		final List<Entry<Row, Color>> rowAndColors = List.of(entry(Row.SECOND, Color.WHITE),
			entry(Row.SEVENTH, Color.BLACK));
		for (Entry<Row, Color> rowAndColor : rowAndColors) {
			pieces.putAll(fillRowWith(rowAndColor));
		}
		pieces.putAll(createPiecesWithoutPawn());
		return pieces;
	}

	private static Map<Position, Piece> fillRowWith(Entry<Row, Color> rowAndColor) {
		final Row row = rowAndColor.getKey();
		final Color color = rowAndColor.getValue();
		return Arrays.stream(Column.values())
			.map(column -> new Position(row, column))
			.collect(Collectors.toMap(Function.identity(), p -> new Pawn(color)));
	}

	private static Map<Position, Piece> createPiecesWithoutPawn() {
		return Stream.of(entry(Row.FIRST, Color.WHITE), entry(Row.EIGHTH, Color.BLACK))
			.map(Board::createLineOf)
			.flatMap(line -> line.entrySet().stream())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	private static Map<Position, Piece> createLineOf(final Entry<Row, Color> rowAndColor) {
		Map<Position, Piece> singleLine = new HashMap<>();
		final Row row = rowAndColor.getKey();
		final Color color = rowAndColor.getValue();
		singleLine.put(new Position(row, Column.a), new Rook(color));
		singleLine.put(new Position(row, Column.b), new Knight(color));
		singleLine.put(new Position(row, Column.c), new Bishop(color));
		singleLine.put(new Position(row, Column.d), new Queen(color));
		singleLine.put(new Position(row, Column.e), new King(color));
		singleLine.put(new Position(row, Column.f), new Bishop(color));
		singleLine.put(new Position(row, Column.g), new Knight(color));
		singleLine.put(new Position(row, Column.h), new Rook(color));
		return singleLine;
	}

	public Map<Position, Piece> getPieces() {
		return pieces;
	}
}
