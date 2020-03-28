package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.File;
import chess.domain.board.Position;

public class WhitePiecesFactory {
	public static WhitePieces create() {
		Map<Position, Piece> pieces = new HashMap<>();

		initWhitePawn(pieces);
		initWhiteRook(pieces);
		initWhiteBishop(pieces);
		initWhiteKnight(pieces);
		initWhiteQueen(pieces);
		initWhiteKing(pieces);

		return new WhitePieces(pieces);
	}

	private static void initWhiteKing(Map<Position, Piece> pieces) {
		pieces.put(Position.of("e1"), new King("k"));
	}

	private static void initWhiteQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("d1"), new Queen("q"));
	}

	private static void initWhiteKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("b1"), new Knight("n"));
		pieces.put(Position.of("g1"), new Knight("n"));
	}

	private static void initWhiteBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("c1"), new Bishop("b"));
		pieces.put(Position.of("f1"), new Bishop("b"));
	}

	private static void initWhiteRook(Map<Position, Piece> pieces) {
		pieces.put(Position.of("a1"), new Rook("r"));
		pieces.put(Position.of("h1"), new Rook("r"));
	}

	private static void initWhitePawn(Map<Position, Piece> pieces) {
		for (File file : File.values()) {
			pieces.put(Position.of(file.getFile() + "2"), new Pawn("p"));
		}
	}
}
