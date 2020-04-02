package chess.domain.piece;

import java.util.Map;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

public class BlankPieceFactory {
	public static void create(Map<Position, Piece> pieces) {
		createByRank(pieces);
	}

	private static void createByRank(Map<Position, Piece> pieces) {
		for (Rank rank : Rank.values()) {
			createByFile(rank, pieces);
		}
	}

	private static void createByFile(Rank rank, Map<Position, Piece> pieces) {
		for (File file : File.values()) {
			pieces.put(Position.of(file, rank), Blank.of());
		}
	}
}
