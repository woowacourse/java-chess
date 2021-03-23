package chess.domain.piece.fixeddistance;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.feature.Color;
import chess.domain.piece.feature.Type;

import java.util.List;

public class King extends FixedDistancePiece {
	public King(Color color, Position position) {
		super(color, position);
		this.type = Type.KING;
	}

	@Override
	public List<Direction> directions() {
		return Direction.everyDirection();
	}
}
