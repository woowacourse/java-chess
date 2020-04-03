package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class PiecesFactory {
	public static void createBlankPieces(Map<Position, Piece> pieces) {
		createBlankPiecesByRank(pieces);
	}

	private static void createBlankPiecesByRank(Map<Position, Piece> pieces) {
		for (Row row : Row.values()) {
			createBlankPiecesByFile(row, pieces);
		}
	}

	private static void createBlankPiecesByFile(Row row, Map<Position, Piece> pieces) {
		for (Column column : Column.values()) {
			pieces.put(Position.of(column, row), Blank.of());
		}
	}

	public static void createBlackPieces(Color color, Map<Position, Piece> pieces) {
		initKing("e8", "K", color, pieces);
		initQueen("d8", "Q", color, pieces);
		initKnight("b8", "N", color, pieces);
		initKnight("g8", "N", color, pieces);
		initBishop("c8", "B", color, pieces);
		initBishop("f8", "B", color, pieces);
		initRook("a8", "R", color, pieces);
		initRook("h8", "R", color, pieces);
		initPawn("7", "P", color, pieces);
	}

	public static void createWhitePieces(Color color, Map<Position, Piece> pieces) {
		initKing("e1", "k", color, pieces);
		initQueen("d1", "q", color, pieces);
		initKnight("b1", "n", color, pieces);
		initKnight("g1", "n", color, pieces);
		initBishop("c1", "b", color, pieces);
		initBishop("f1", "b", color, pieces);
		initRook("a1", "r", color, pieces);
		initRook("h1", "r", color, pieces);
		initPawn("2", "p", color, pieces);
	}

	private static void initKing(String position, String symbol, Color color, Map<Position, Piece> pieces) {
		pieces.put(Position.of(position), new King(color, symbol));
	}

	private static void initQueen(String position, String symbol, Color color, Map<Position, Piece> pieces) {
		pieces.put(Position.of(position), new Queen(color, symbol));
	}

	private static void initKnight(String position, String symbol, Color color, Map<Position, Piece> pieces) {
		pieces.put(Position.of(position), new Knight(color, symbol));
	}

	private static void initBishop(String position, String symbol, Color color, Map<Position, Piece> pieces) {
		pieces.put(Position.of(position), new Bishop(color, symbol));
	}

	private static void initRook(String position, String symbol, Color color, Map<Position, Piece> pieces) {
		pieces.put(Position.of(position), new Rook(color, symbol));
	}

	private static void initPawn(String position, String symbol, Color color, Map<Position, Piece> pieces) {
		for (Column column : chess.domain.board.Column.values()) {
			pieces.put(Position.of(column.getColumn() + position), new Pawn(color, symbol));
		}
	}
}
