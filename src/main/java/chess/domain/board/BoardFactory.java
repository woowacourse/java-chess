package chess.domain.board;

import static chess.domain.piece.Pawn.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;

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
		pieces.put(Position.of("A1"), new Rook("r", Color.WHITE));
		pieces.put(Position.of("H1"), new Rook("r", Color.WHITE));
		pieces.put(Position.of("A8"), new Rook("R", Color.BLACK));
		pieces.put(Position.of("H8"), new Rook("R", Color.BLACK));
		return pieces;
	}

	public static Map<Position, Piece> initializeKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("B1"), new Knight("n", Color.WHITE));
		pieces.put(Position.of("G1"), new Knight("n", Color.WHITE));
		pieces.put(Position.of("B8"), new Knight("N", Color.BLACK));
		pieces.put(Position.of("G8"), new Knight("N", Color.BLACK));
		return pieces;
	}

	public static Map<Position, Piece> initializeBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("C1"), new Bishop("b", Color.WHITE));
		pieces.put(Position.of("F1"), new Bishop("b", Color.WHITE));
		pieces.put(Position.of("C8"), new Bishop("B", Color.BLACK));
		pieces.put(Position.of("F8"), new Bishop("B", Color.BLACK));
		return pieces;
	}

	public static Map<Position, Piece> initializeKingQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("D1"), new Queen("q", Color.WHITE));
		pieces.put(Position.of("E1"), new King("k", Color.WHITE));
		pieces.put(Position.of("D8"), new Queen("Q", Color.BLACK));
		pieces.put(Position.of("E8"), new King("K", Color.BLACK));
		return pieces;
	}

	public static Map<Position, Piece> initializePawn(Map<Position, Piece> pieces) {
		Column.columnNames()
			.stream()
			.map(Column::getName)
			.forEach(x -> {
				pieces.put(Position.of(x + WHITE_PAWN_ROW), new Pawn("p", Color.WHITE));
				pieces.put(Position.of(x + BLACK_PAWN_ROW), new Pawn("P", Color.BLACK));
			});

		return pieces;
	}
}
