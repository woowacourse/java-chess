package chess.piece;

import java.util.HashMap;
import java.util.Map;

import chess.piece.type.Bishop;
import chess.piece.type.King;
import chess.piece.type.Knight;
import chess.piece.type.Pawn;
import chess.piece.type.Queen;
import chess.piece.type.Rook;

public class SizeInformation {
	private static final Map<Class, Integer> map = new HashMap<>();

	static {
		map.put(Pawn.class, 8);
		map.put(Rook.class, 2);
		map.put(Knight.class, 2);
		map.put(King.class, 1);
		map.put(Queen.class, 1);
		map.put(Bishop.class, 2);
	}

	public static Map<Class, Integer> getMap() {
		return map;
	}
}
