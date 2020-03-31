package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.piece.state.Initial;

public class BlackPiecesFactory {
	public static Pieces create() {
		Map<Position, Piece> pieces = new HashMap<>();

		initBlackPawn(pieces);
		initBlackRook(pieces);
		initBlackBishop(pieces);
		initBlackKnight(pieces);
		initBlackQueen(pieces);
		initBlackKing(pieces);

		return new Pieces(pieces);
	}

	private static void initBlackKing(Map<Position, Piece> pieces) {
		pieces.put(Position.of("e8"), new King(new Initial(), "K"));
	}

	private static void initBlackQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("d8"), new Queen(new Initial(), "Q"));
	}

	private static void initBlackKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("b8"), new Knight(new Initial(), "N"));
		pieces.put(Position.of("g8"), new Knight(new Initial(), "N"));
	}

	private static void initBlackBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("c8"), new Bishop(new Initial(), "B"));
		pieces.put(Position.of("f8"), new Bishop(new Initial(), "B"));
	}

	private static void initBlackRook(Map<Position, Piece> pieces) {
		pieces.put(Position.of("a8"), new Rook(new Initial(), "R"));
		pieces.put(Position.of("h8"), new Rook(new Initial(), "R"));
	}

	private static void initBlackPawn(Map<Position, Piece> pieces) {
		for (File file : File.values()) {
			pieces.put(Position.of(file.getFile() + "7"), new Pawn(new Initial(), "P"));
		}
	}
}
