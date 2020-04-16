package chess.domain.chessPiece.pieceStrategy;

import static chess.domain.chessPiece.pieceType.PieceDirection.*;

import chess.domain.position.Position;

public class QueenStrategy implements PieceStrategy {

	@Override
	public boolean canLeap() {
		return false;
	}

	@Override
	public boolean canMove(final Position sourcePosition, final Position targetPosition, final int pawnMovableRange) {
		return canMoveDirection(sourcePosition, targetPosition) && canMoveRange();
	}

	private boolean canMoveDirection(final Position sourcePosition, final Position targetPosition) {
		return ALL.getMovableDirections().stream()
			.anyMatch(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition));
	}

	private boolean canMoveRange() {
		return true;
	}

	@Override
	public boolean canCatch(final Position sourcePosition, final Position targetPosition) {
		return ALL.getCatchableDirections().stream()
			.anyMatch(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition));
	}

}
