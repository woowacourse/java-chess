package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
	private static final Map<String, Piece> PIECE_CACHE = new HashMap<>();

	static {
		PIECE_CACHE.put("WHITE_KING", new King(WHITE));
		PIECE_CACHE.put("BLACK_KING", new King(BLACK));
		PIECE_CACHE.put("WHITE_QUEEN", new Queen(WHITE));
		PIECE_CACHE.put("BLACK_QUEEN", new Queen(BLACK));
		PIECE_CACHE.put("WHITE_ROOK", new Rook(WHITE));
		PIECE_CACHE.put("BLACK_ROOK", new Rook(BLACK));
		PIECE_CACHE.put("WHITE_KNIGHT", new Knight(WHITE));
		PIECE_CACHE.put("BLACK_KNIGHT", new Knight(BLACK));
		PIECE_CACHE.put("WHITE_BISHOP", new Bishop(WHITE));
		PIECE_CACHE.put("BLACK_BISHOP", new Bishop(BLACK));
		PIECE_CACHE.put("WHITE_PAWN", new WhitePawn());
		PIECE_CACHE.put("BLACK_PAWN", new BlackPawn());
	}

	private PieceFactory() {
	}

	public static Piece createByName(String pieceName) {
		System.err.println(pieceName);
		if (!PIECE_CACHE.containsKey(pieceName)) {
			throw new IllegalArgumentException("해당하는 piece가 존재하지 않습니다.");
		}
		return PIECE_CACHE.get(pieceName);
	}
}
