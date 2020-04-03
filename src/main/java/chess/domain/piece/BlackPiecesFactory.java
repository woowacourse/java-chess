package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.board.Column;
import chess.domain.board.Position;

public class BlackPiecesFactory {
	public static void create(Map<Position, Piece> pieces) {
		initPawn(pieces);
		initRook(pieces);
		initBishop(pieces);
		initKnight(pieces);
		initQueen(pieces);
		initKing(pieces);
	}

	private static void initKing(Map<Position, Piece> pieces) {
		pieces.put(Position.of("e8"), new King(Color.BLACK, "K"));
	}

	private static void initQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("d8"), new Queen(Color.BLACK, "Q"));
	}

	private static void initKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("b8"), new Knight(Color.BLACK, "N"));
		pieces.put(Position.of("g8"), new Knight(Color.BLACK, "N"));
	}

	private static void initBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("c8"), new Bishop(Color.BLACK, "B"));
		pieces.put(Position.of("f8"), new Bishop(Color.BLACK, "B"));
	}

	private static void initRook(Map<Position, Piece> pieces) {
		pieces.put(Position.of("a8"), new Rook(Color.BLACK, "R"));
		pieces.put(Position.of("h8"), new Rook(Color.BLACK, "R"));
	}

	private static void initPawn(Map<Position, Piece> pieces) {
		for (Column column : chess.domain.board.Column.values()) {
			pieces.put(Position.of(column.getColumn() + "7"), new Pawn(Color.BLACK, "P"));
		}
	}
}
