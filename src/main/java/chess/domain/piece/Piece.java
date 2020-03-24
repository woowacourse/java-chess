package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class Piece {
	private Side side;
	private Position position;

	public Piece(Side side, Position position) {
		this.side = side;
		this.position = position;
	}
}
