package chess.domain.chessPiece.pieceType.nonLeapablePieceType;

import java.util.ArrayList;
import java.util.List;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

public abstract class NonLeapableChessPiece extends ChessPiece {

	protected final List<MoveDirection> movableDirections = new ArrayList<>();

	protected NonLeapableChessPiece(final PieceColor pieceColor, final PieceState pieceState) {
		super(pieceColor, pieceState);
	}

	@Override
	public boolean canLeap() {
		return false;
	}

	@Override
	protected boolean isMovable(final Position sourcePosition, final Position targetPosition) {
		return canMoveDirection(sourcePosition, targetPosition) && canMoveRange(sourcePosition, targetPosition);
	}

	private boolean canMoveDirection(final Position sourcePosition, final Position targetPosition) {
		return movableDirections.stream()
			.anyMatch(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition));
	}

	protected abstract boolean canMoveRange(final Position sourcePosition, final Position targetPosition);

	@Override
	protected boolean isCatchable(final Position sourcePosition, final Position targetPosition) {
		return isMovable(sourcePosition, targetPosition);
	}

}
