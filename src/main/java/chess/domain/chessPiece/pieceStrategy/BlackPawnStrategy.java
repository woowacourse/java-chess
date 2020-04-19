package chess.domain.chessPiece.pieceStrategy;

import static chess.domain.chessPiece.pieceType.PieceDirection.*;

import chess.domain.position.Position;

public class BlackPawnStrategy implements PieceStrategy {

	@Override
	public boolean canLeap() {
		return false;
	}

	@Override
	public boolean canMove(final Position sourcePosition, final Position targetPosition, final int pawnMovableRange) {
		return canMoveDirection(sourcePosition, targetPosition) && canMoveRange(sourcePosition, targetPosition,
			pawnMovableRange);
	}

	private boolean canMoveDirection(final Position sourcePosition, final Position targetPosition) {
		return BLACK_PAWN.getMovableDirections().stream()
			.anyMatch(moveDirection -> moveDirection.isSameDirectionFrom(sourcePosition, targetPosition));
	}

	private boolean canMoveRange(final Position sourcePosition, final Position targetPosition,
		final int pawnMovableRange) {
		int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return (chessFileGap == 0) && (chessRankGap <= pawnMovableRange);
	}

	@Override
	public boolean canCatch(final Position sourcePosition, final Position targetPosition) {
		return canCatchDirection(sourcePosition, targetPosition) && canCatchRange(sourcePosition, targetPosition);
	}

	private boolean canCatchDirection(Position sourcePosition, Position targetPosition) {
		return BLACK_PAWN.getCatchableDirections().stream()
			.anyMatch(catchableDirections -> catchableDirections.isSameDirectionFrom(sourcePosition, targetPosition));
	}

	private boolean canCatchRange(Position sourcePosition, Position targetPosition) {
		int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return (chessFileGap == 1) && (chessRankGap == 1);
	}

}
