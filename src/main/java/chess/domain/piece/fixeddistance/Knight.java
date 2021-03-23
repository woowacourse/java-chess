package chess.domain.piece.fixeddistance;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.feature.Color;
import chess.domain.piece.feature.Type;

import java.util.List;

public class Knight extends FixedDistancePiece {
	public Knight(Color color, Position position) {
		super(color, position);
		this.type = Type.KNIGHT;
	}

	@Override
	public List<Direction> directions() {
		return Direction.knightDirection();
	}
}
