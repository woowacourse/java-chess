package chess.domain.game;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.pieces.Pieces;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.Map;

public class Board {
	private final Map<Position, Piece> board;

	public Board(Pieces pieces) {
		board = new HashMap<>();
		createBoard(pieces);
	}

	private void createBoard(Pieces pieces) {
		for (Column column : Column.values()) {
			createRowLine(pieces, column);
		}
	}

	private void createRowLine(Pieces pieces, Column column) {
		for (Row row : Row.values()) {
			Position position = PositionFactory.of(row, column);
			board.put(position, pieces.findBy(position));
			// TODO: 2020/03/29 Blank를 매핑하는 대신, 아예 연산을 생략하는 것은? 웹에서 구현할떄 필요할듯하다.
		}
	}

	public Piece getPieceBy(Position position) {
		if (board.containsKey(position)) {
			return board.get(position);
		}
		return new Blank();
	}
}
