package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public class Rook extends Piece implements FlexibleDistanceMovable {

	public Rook(Color color, Position position) {
		super(color, position);
		this.type = Type.ROOK;
	}

	@Override
	public List<Direction> directions() {
		return Direction.linearDirection();
	}
}
