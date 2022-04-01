package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;
import chess.domain.piece.movestrategy.RepeatableMoveStrategy;

public class Bishop extends Piece {

	public Bishop(Symbol symbol, Team team) {
		super(symbol, team);
	}

	@Override
	public boolean hasDirection(Direction direction) {
		return Direction.bishopDirection()
			.contains(direction);
	}

	@Override
	public MoveStrategy moveStrategy() {
		return new RepeatableMoveStrategy();
	}
}
