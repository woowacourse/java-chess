package chess.util;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Piece;

public class PieceNameConverter {
	private static final Map<String, String> NAME_MAPPER = new HashMap<>();

	static {
		NAME_MAPPER.put("WHITEK", "WHITE_KING");
		NAME_MAPPER.put("BLACKK", "BLACK_KING");
		NAME_MAPPER.put("WHITEQ", "WHITE_QUEEN");
		NAME_MAPPER.put("BLACKQ", "BLACK_QUEEN");
		NAME_MAPPER.put("WHITER", "WHITE_ROOK");
		NAME_MAPPER.put("BLACKR", "BLACK_ROOK");
		NAME_MAPPER.put("WHITEN", "WHITE_KNIGHT");
		NAME_MAPPER.put("BLACKN", "BLACK_KNIGHT");
		NAME_MAPPER.put("WHITEB", "WHITE_BISHOP");
		NAME_MAPPER.put("BLACKB", "BLACK_BISHOP");
		NAME_MAPPER.put("WHITEP", "WHITE_PAWN");
		NAME_MAPPER.put("BLACKP", "BLACK_PAWN");
	}

	private PieceNameConverter() {
	}

	public static String toPieceType(Piece piece) {
		return NAME_MAPPER.get(piece.getColor() + piece.getName().toUpperCase());
	}
}
