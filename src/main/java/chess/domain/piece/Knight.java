package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;
import chess.domain.piece.movestrategy.NonRepeatableMoveStrategy;

public class Knight extends Piece {

	public Knight(Symbol symbol, Team team) {
		super(symbol, team);
	}

	@Override
	public boolean hasDirection(Direction direction) {
		return Direction.knightDirection()
			.contains(direction);
	}

	@Override
	public MoveStrategy moveStrategy() {
		return new NonRepeatableMoveStrategy();
	}
}
