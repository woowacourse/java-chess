package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

public final class King extends Piece {

	private static final int KING_SCORE = 0;

	public King(final Team team) {
		super(team);
	}
	
	@Override
	protected void validateDirection(final Position source, final Position target, final Piece targetPiece) {
		List<Direction> directions = Direction.getKingDirection();
		List<Position> movablePositions = source.getArrivalPositionsByDirections(directions);
		if (!movablePositions.contains(target)) {
			throw new IllegalArgumentException(MOVEMENT_ERROR);
		}
	}

	@Override
	public boolean isBlank() {
		return false;
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public boolean isKing() {
		return true;
	}

	@Override
	public double getScore() {
		return KING_SCORE;
	}
}
