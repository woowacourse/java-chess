package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.File;
import chess.domain.board.Position;

public class BlackPiecesFactory {
	public static BlackPieces create() {
		Map<Position, Piece> pieces = new HashMap<>();

		initBlackPawn(pieces);
		initBlackRook(pieces);
		initBlackBishop(pieces);
		initBlackKnight(pieces);
		initBlackQueen(pieces);
		initBlackKing(pieces);

		return new BlackPieces(pieces);
	}

	private static void initBlackKing(Map<Position, Piece> pieces) {
		pieces.put(Position.of("e8"), new King("K"));
	}

	private static void initBlackQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("d8"), new Queen("Q"));
	}

	private static void initBlackKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("b8"), new Knight("N"));
		pieces.put(Position.of("g8"), new Knight("N"));
	}

	private static void initBlackBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("c8"), new Bishop("B"));
		pieces.put(Position.of("f8"), new Bishop("B"));
	}

	private static void initBlackRook(Map<Position, Piece> pieces) {
		pieces.put(Position.of("a8"), new Rook("R"));
		pieces.put(Position.of("h8"), new Rook("R"));
	}

	private static void initBlackPawn(Map<Position, Piece> pieces) {
		for (File file : File.values()) {
			pieces.put(Position.of(file.getFile() + "7"), new Pawn("P"));
		}
	}
}
