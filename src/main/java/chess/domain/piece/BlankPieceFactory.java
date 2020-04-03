package chess.domain.piece;

import java.util.Map;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class BlankPieceFactory {
	public static void create(Map<Position, Piece> pieces) {
		createByRank(pieces);
	}

	private static void createByRank(Map<Position, Piece> pieces) {
		for (Row row : Row.values()) {
			createByFile(row, pieces);
		}
	}

	private static void createByFile(Row row, Map<Position, Piece> pieces) {
		for (Column column : Column.values()) {
			pieces.put(Position.of(column, row), Blank.of());
		}
	}
}
