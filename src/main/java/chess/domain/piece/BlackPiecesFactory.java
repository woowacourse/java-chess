package chess.domain.piece;

import java.util.Map;

import chess.domain.board.File;
import chess.domain.board.Position;

public class BlackPiecesFactory {
	private static final String COLOR = "black";

	public static void create(Map<Position, Piece> pieces) {
		initPawn(pieces);
		initRook(pieces);
		initBishop(pieces);
		initKnight(pieces);
		initQueen(pieces);
		initKing(pieces);
	}

	private static void initKing(Map<Position, Piece> pieces) {
		pieces.put(Position.of("e8"), new King(COLOR, "K"));
	}

	private static void initQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("d8"), new Queen(COLOR, "Q"));
	}

	private static void initKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("b8"), new Knight(COLOR, "N"));
		pieces.put(Position.of("g8"), new Knight(COLOR, "N"));
	}

	private static void initBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("c8"), new Bishop(COLOR, "B"));
		pieces.put(Position.of("f8"), new Bishop(COLOR, "B"));
	}

	private static void initRook(Map<Position, Piece> pieces) {
		pieces.put(Position.of("a8"), new Rook(COLOR, "R"));
		pieces.put(Position.of("h8"), new Rook(COLOR, "R"));
	}

	private static void initPawn(Map<Position, Piece> pieces) {
		for (File file : File.values()) {
			pieces.put(Position.of(file.getFile() + "7"), new Pawn(COLOR, "P"));
		}
	}
}
