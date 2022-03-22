package chess.domain.board;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public class Board {
	private final Map<Coordinate, Piece> value;

	private Board(Map<Coordinate, Piece> value) {
		this.value = value;
	}

	public static Board create() {
		return new Board(InitialBoard.init());
	}
}
