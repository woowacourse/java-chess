package chess.domain.board;

import static chess.domain.piece.Color.*;
import static chess.domain.piece.PawnMovingStrategy.*;
import static chess.domain.piece.PieceType.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Piece;
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
		pieces.put(Position.of("A1"), new Piece("r", WHITE, ROOK));
		pieces.put(Position.of("H1"), new Piece("r", WHITE, ROOK));
		pieces.put(Position.of("A8"), new Piece("R", BLACK, ROOK));
		pieces.put(Position.of("H8"), new Piece("R", BLACK, ROOK));
		return pieces;
	}

	public static Map<Position, Piece> initializeKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("B1"), new Piece("n", WHITE, KNIGHT));
		pieces.put(Position.of("G1"), new Piece("n", WHITE, KNIGHT));
		pieces.put(Position.of("B8"), new Piece("N", BLACK, KNIGHT));
		pieces.put(Position.of("G8"), new Piece("N", BLACK, KNIGHT));
		return pieces;
	}

	public static Map<Position, Piece> initializeBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("C1"), new Piece("b", WHITE, BISHOP));
		pieces.put(Position.of("F1"), new Piece("b", WHITE, BISHOP));
		pieces.put(Position.of("C8"), new Piece("B", BLACK, BISHOP));
		pieces.put(Position.of("F8"), new Piece("B", BLACK, BISHOP));
		return pieces;
	}

	public static Map<Position, Piece> initializeKingQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("D1"), new Piece("q", WHITE, QUEEN));
		pieces.put(Position.of("E1"), new Piece("k", WHITE, KING));
		pieces.put(Position.of("D8"), new Piece("Q", BLACK, QUEEN));
		pieces.put(Position.of("E8"), new Piece("K", BLACK, KING));
		return pieces;
	}

	public static Map<Position, Piece> initializePawn(Map<Position, Piece> pieces) {
		Column.columnNames()
			.stream()
			.map(Column::getName)
			.forEach(x -> {
				pieces.put(Position.of(x + WHITE_PAWN_ROW),
					new Piece("p", WHITE, WHITE_PAWN));
				pieces.put(Position.of(x + BLACK_PAWN_ROW),
					new Piece("P", BLACK, BLACK_PAWN));
			});

		return pieces;
	}
}
