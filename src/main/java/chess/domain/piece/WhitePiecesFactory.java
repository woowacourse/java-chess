package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.board.File;
import chess.domain.board.Position;

public class WhitePiecesFactory {
	public static void create(Map<Position, Piece> pieces) {
		initPawn(pieces);
		initRook(pieces);
		initBishop(pieces);
		initKnight(pieces);
		initQueen(pieces);
		initKing(pieces);
	}

	private static void initKing(Map<Position, Piece> pieces) {
		pieces.put(Position.of("e1"), new King(Color.WHITE, "k"));
	}

	private static void initQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("d1"), new Queen(Color.WHITE, "q"));
	}

	private static void initKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("b1"), new Knight(Color.WHITE, "n"));
		pieces.put(Position.of("g1"), new Knight(Color.WHITE, "n"));
	}

	private static void initBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("c1"), new Bishop(Color.WHITE, "b"));
		pieces.put(Position.of("f1"), new Bishop(Color.WHITE, "b"));
	}

	private static void initRook(Map<Position, Piece> pieces) {
		pieces.put(Position.of("a1"), new Rook(Color.WHITE, "r"));
		pieces.put(Position.of("h1"), new Rook(Color.WHITE, "r"));
	}

	private static void initPawn(Map<Position, Piece> pieces) {
		for (File file : File.values()) {
			pieces.put(Position.of(file.getFile() + "2"), new Pawn(Color.WHITE, "p"));
		}
	}
}
