package chess.domain.piece;

import java.util.List;

import chess.domain.position.Direction;
import chess.domain.position.UnitDirection;

public abstract class MovingUnitSquarePiece extends Piece {

	private final List<UnitDirection> directions;

	MovingUnitSquarePiece(Color color, double score, List<UnitDirection> directions) {
		super(color, score);
		this.directions = directions;
	}

	@Override
	public final boolean canMove(Direction direction, Piece target) {
		checkSameTeam(target);
		return direction.hasSame(directions);
	}
}
