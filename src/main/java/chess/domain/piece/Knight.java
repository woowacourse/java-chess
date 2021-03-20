package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public class Knight extends Piece implements FixedDistanceMovable {

	public Knight(Color color, Position position) {
		super(color, position);
		this.type = Type.KNIGHT;
	}

	@Override
	public List<Direction> directions() {
		return Direction.knightDirection();
	}
}
