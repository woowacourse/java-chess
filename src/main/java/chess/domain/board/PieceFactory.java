package chess.domain.board;

import static chess.domain.piece.Color.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class PieceFactory {
	private PieceFactory() {
	}

	private static Map<String, Piece> PIECE_CACHE = new HashMap<>();

	static {
		PIECE_CACHE.put("p", new Pawn(WHITE));
		PIECE_CACHE.put("r", new Rook(WHITE));
		PIECE_CACHE.put("n", new Knight(WHITE));
		PIECE_CACHE.put("b", new Bishop(WHITE));
		PIECE_CACHE.put("q", new Queen(WHITE));
		PIECE_CACHE.put("k", new King(WHITE));
		PIECE_CACHE.put("P", new Pawn(BLACK));
		PIECE_CACHE.put("R", new Rook(BLACK));
		PIECE_CACHE.put("N", new Knight(BLACK));
		PIECE_CACHE.put("B", new Bishop(BLACK));
		PIECE_CACHE.put("Q", new Queen(BLACK));
		PIECE_CACHE.put("K", new King(BLACK));
	}

	public static Piece of(String name) {
		Piece piece = PIECE_CACHE.get(name);
		if (piece == null) {
			throw new IllegalArgumentException("그런 Piece 는 존재하지 않습니다.");
		}

		return piece;
	}
}
