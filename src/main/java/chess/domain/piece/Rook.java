package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;
import chess.domain.piece.movestrategy.RepeatableMoveStrategy;

public class Rook extends Piece {
	public Rook(Symbol symbol, Team team) {
		super(symbol, team);
	}

	@Override
	public boolean hasDirection(Direction direction) {
		return Direction.rookDirection()
			.contains(direction);
	}

	@Override
	public MoveStrategy moveStrategy() {
		return new RepeatableMoveStrategy();
	}
}
