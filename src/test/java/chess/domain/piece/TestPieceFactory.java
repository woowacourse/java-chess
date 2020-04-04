package chess.domain.piece;

import chess.domain.position.Position;

public class TestPieceFactory {
	public static Piece createKing(Position position, Color color) {
		return new Piece(position, PieceType.KING, color);
	}

	public static Piece createQueen(Position position, Color color) {
		return new Piece(position, PieceType.QUEEN, color);
	}

	public static Piece createKnight(Position position, Color color) {
		return new Piece(position, PieceType.KNIGHT, color);
	}

	public static Piece createRook(Position position, Color color) {
		return new Piece(position, PieceType.ROOK, color);
	}

	public static Piece createBishop(Position position, Color color) {
		return new Piece(position, PieceType.BISHOP, color);
	}

	public static Piece createPawn(Position position, Color color) {
		if (color.isWhite()) {
			return new Piece(position, PieceType.WHITE_PAWN, color);
		}
		return new Piece(position, PieceType.BLACK_PAWN, color);
	}
}
