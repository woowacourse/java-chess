package chess.domain.piece.mulitiplemovepiece;

import java.util.List;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.UnitDirection;

public abstract class MovingMultipleSquarePiece extends Piece {
	private final List<UnitDirection> directions;

	MovingMultipleSquarePiece(Color color, double score, List<UnitDirection> directions) {
		super(color, score);
		this.directions = directions;
	}

	@Override
	public final boolean canMove(Direction direction, Piece target) {
		checkSameTeam(target);
		return direction.hasMultiple(directions);
	}
}
