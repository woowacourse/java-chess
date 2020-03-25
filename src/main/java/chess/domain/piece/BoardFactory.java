package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Board;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class BoardFactory {
	private BoardFactory() {
	}

	public static Board create() {
		Map<Position, Piece> pieces = new HashMap<>();
		initialize(pieces);
		return new Board(pieces);
	}

	private static void initialize(Map<Position, Piece> pieces) {
		initializeRook(pieces);
		initializeKnight(pieces);
		initializeBishop(pieces);
		initializeKingQueen(pieces);
		initializePawn(pieces);
	}

	public static Map<Position, Piece> initializeRook(Map<Position, Piece> pieces) {
		pieces.put(Position.of(Column.of("A"), Row.of("1")), new Rook("r", Color.WHITE));
		pieces.put(Position.of(Column.of("H"), Row.of("1")), new Rook("r", Color.WHITE));
		pieces.put(Position.of(Column.of("A"), Row.of("8")), new Rook("R", Color.BLACK));
		pieces.put(Position.of(Column.of("H"), Row.of("8")), new Rook("R", Color.BLACK));
		return pieces;
	}

	public static Map<Position, Piece> initializeKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of(Column.of("B"), Row.of("1")), new Knight("n", Color.WHITE));
		pieces.put(Position.of(Column.of("G"), Row.of("1")), new Knight("n", Color.WHITE));
		pieces.put(Position.of(Column.of("B"), Row.of("8")), new Knight("N", Color.BLACK));
		pieces.put(Position.of(Column.of("G"), Row.of("8")), new Knight("N", Color.BLACK));
		return pieces;
	}

	public static Map<Position, Piece> initializeBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of(Column.of("C"), Row.of("1")), new Bishop("b", Color.WHITE));
		pieces.put(Position.of(Column.of("F"), Row.of("1")), new Bishop("b", Color.WHITE));
		pieces.put(Position.of(Column.of("C"), Row.of("8")), new Bishop("B", Color.BLACK));
		pieces.put(Position.of(Column.of("F"), Row.of("8")), new Bishop("B", Color.BLACK));
		return pieces;
	}

	public static Map<Position, Piece> initializeKingQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of(Column.of("D"), Row.of("1")), new Queen("q", Color.WHITE));
		pieces.put(Position.of(Column.of("E"), Row.of("1")), new King("k", Color.WHITE));
		pieces.put(Position.of(Column.of("D"), Row.of("8")), new Queen("Q", Color.BLACK));
		pieces.put(Position.of(Column.of("E"), Row.of("8")), new King("K", Color.BLACK));
		return pieces;
	}

	public static Map<Position, Piece> initializePawn(Map<Position, Piece> pieces) {
		pieces.put(Position.of(Column.of("B"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("A"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("C"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("D"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("E"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("F"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("G"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("H"), Row.of("2")), new Pawn("p", Color.WHITE));

		pieces.put(Position.of(Column.of("A"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("B"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("C"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("D"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("E"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("F"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("G"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("H"), Row.of("7")), new Pawn("P", Color.BLACK));
		return pieces;
	}
}
