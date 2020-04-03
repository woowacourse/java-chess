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
		pieces.put(Position.of("e8"), new King(new Initial(), "♚"));
	}

	private static void initBlackQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("d8"), new Queen(new Initial(), "♛"));
	}

	private static void initBlackKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("b8"), new Knight(new Initial(), "♞"));
		pieces.put(Position.of("g8"), new Knight(new Initial(), "♞"));
	}

	private static void initBlackBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("c8"), new Bishop(new Initial(), "♝"));
		pieces.put(Position.of("f8"), new Bishop(new Initial(), "♝"));
	}

	private static void initBlackRook(Map<Position, Piece> pieces) {
		pieces.put(Position.of("a8"), new Rook(new Initial(), "♜"));
		pieces.put(Position.of("h8"), new Rook(new Initial(), "♜"));
	}

	private static void initBlackPawn(Map<Position, Piece> pieces) {
		for (File file : File.values()) {
			pieces.put(Position.of(file.getFile() + "7"), new Pawn(new Initial(), "♟"));
		}
	}
}
