package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;
import chess.domain.piece.movestrategy.PawnMoveStrategy;

public class Pawn extends Piece {

	public Pawn(Symbol symbol, Team team) {
		super(symbol, team);
	}

	@Override
	public boolean hasDirection(Direction direction) {
		if (team.isSame(Team.BLACK)) {
			return Direction.blackPawnDirection()
				.contains(direction);
		}
		return Direction.whitePawnDirection()
			.contains(direction);
	}

	@Override
	public MoveStrategy moveStrategy() {
		return new PawnMoveStrategy();
	}
}
