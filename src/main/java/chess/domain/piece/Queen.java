package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public class Queen extends Piece implements FlexibleDistanceMovable {
	public Queen(Color color, Position position) {
		super(color, position);
		this.type = Type.QUEEN;
	}

	@Override
	public List<Direction> directions() {
		return Direction.everyDirection();
	}
}
