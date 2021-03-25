package chess.domain.piece.flexibledistance;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.feature.Color;
import chess.domain.piece.feature.Type;

import java.util.List;

public class Queen extends FlexibleDistancePiece {
	public Queen(Color color, Position position) {
		super(color, position);
		this.type = Type.QUEEN;
	}

	@Override
	public List<Direction> directions() {
		return Direction.everyDirection();
	}
}
